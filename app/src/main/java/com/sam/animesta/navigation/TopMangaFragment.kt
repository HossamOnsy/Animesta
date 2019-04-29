package com.sam.animesta.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.VolleyError

import com.sam.animesta.R
import com.sam.animesta.adapters.TopAdapter
import com.sam.animesta.di.DaggerActivityComponent
import com.sam.animesta.model.TopModel
import com.sam.animesta.presenter.TopAnimeMangaView
import com.sam.animesta.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.fragment_top_anime.*
import timber.log.Timber
import javax.inject.Inject

class TopMangaFragment : Fragment(), TopAnimeMangaView {

    @Inject
    lateinit var mainPresenterImpl: MainPresenterImpl

    lateinit var topAdapter : TopAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_manga, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var component = DaggerActivityComponent.create()
        component.inject(this)


        topAdapter = TopAdapter("Manga",activity,ArrayList())
        rv_top.adapter = topAdapter
        rv_top.layoutManager= GridLayoutManager(activity,2)
        rv_top.setHasFixedSize(false)

        if(progressbar!=null)
        progressbar.visibility = View.VISIBLE
        mainPresenterImpl.topAnimeMangaView = this
        mainPresenterImpl.getTopManga(activity, 1)

    }

    override fun onDataFetch(topModelList: List<TopModel>) {
        if(progressbar!=null)
        progressbar.visibility = View.GONE
        topAdapter.updateList(topModelList)
    }

    override fun errorOccured(error: VolleyError) {
        if(progressbar!=null)
        progressbar.visibility = View.GONE
        Timber.d("Volley Error : ${error.message}")
    }

}
