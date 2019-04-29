package com.sam.animesta.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import com.android.volley.VolleyError
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.source.MediaSource
import com.sam.animesta.databinding.ActivityAnimeDetailsBinding
import com.sam.animesta.di.DaggerActivityComponent
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel
import com.sam.animesta.presenter.AnimeDetails
import com.sam.animesta.presenter.DetailsPresenterImpl
import kotlinx.android.synthetic.main.activity_anime_details.*
import timber.log.Timber
import javax.inject.Inject
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YtFile
import at.huber.youtubeExtractor.YouTubeExtractor








class AnimeDetailsActivity : AppCompatActivity() , AnimeDetails{

    @Inject
    lateinit var detailsPresenter: DetailsPresenterImpl

    lateinit var binding : ActivityAnimeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.sam.animesta.R.layout.activity_anime_details)
        var component =DaggerActivityComponent.create()
        component.inject(this)

        detailsPresenter.animeDetails=this
        if(intent.hasExtra("id")) {
            detailsPresenter.getDetailsModel(intent.getIntExtra("id", 1), this)

            progressbar.visibility = VISIBLE
            container.visibility = GONE
        }else{
            finish()
        }
    }


    override fun onModelFetched(animeDetailsModel: AnimeDetailsModel) {
        binding.topAnime = animeDetailsModel
        Glide.with(this).load(animeDetailsModel.image_url).into(iv_detail_image)
        container.visibility= VISIBLE
        progressbar.visibility = GONE
        if(animeDetailsModel.trailerUrl!=null)
         initializePlayer(animeDetailsModel.trailerUrl)

    }

    fun initializePlayer(url: String) {
       var  player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this),
            DefaultTrackSelector(), DefaultLoadControl()
        )
        object : YouTubeExtractor(this) {
            public override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta) {
                if (ytFiles != null) {
                    val itag = 22
                    val downloadUrl = ytFiles.get(itag).url

                    var uri = Uri.parse(downloadUrl);
                    var mediaSource = buildMediaSource(uri);
                    player.prepare(mediaSource, true, false);


                }else {
                    var uri = Uri.parse(url);
                    var mediaSource = buildMediaSource(uri);
                    player.prepare(mediaSource, true, false);

                }
            }
        }.extract(url, true, true)

        video_view.setPlayer(player);
        var uri = Uri.parse(url);
        var mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow, playbackPosition);
    }




    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer-codelab")
        ).createMediaSource(uri)
    }

    override fun errorOccured(error: VolleyError) {
        progressbar.visibility = GONE

        Timber.d("Errorrrrrr ->  ${error.message}")

    }


}
