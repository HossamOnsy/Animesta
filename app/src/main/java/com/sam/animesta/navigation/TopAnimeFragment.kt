package com.sam.animesta.navigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.sam.animesta.R
import com.sam.animesta.adapters.TopAdapter
import com.sam.animesta.di.DaggerActivityComponent
import com.sam.animesta.model.TopModel
import com.sam.animesta.presenter.MainPresenterImpl
import com.sam.animesta.presenter.TopAnimeMangaView
import com.sam.animesta.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_top_anime.*
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TopAnimeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TopAnimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TopAnimeFragment : Fragment(), TopAnimeMangaView {

    @Inject
    lateinit var mainPresenterImpl: MainPresenterImpl

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var page = 1

    lateinit var topAdapter: TopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_anime, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var component = DaggerActivityComponent.create()
        component.inject(this)


        topAdapter = TopAdapter("Anime", activity, ArrayList())
        rv_top.adapter = topAdapter
        rv_top.layoutManager = GridLayoutManager(activity, 2)
        rv_top.setHasFixedSize(false)

        var sharedPref =  activity?.getSharedPreferences(
            "PREF_FILE", Context.MODE_PRIVATE)


        page = sharedPref!!.getInt("page",1)


        rv_top.addOnScrollListener(object :
            PaginationScrollListener(rv_top.layoutManager as GridLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                //you have to call loadmore items to get more data
                if (!isLastPage)
                    getMoreItems()

            }
        })

        if (progressbar != null)
            progressbar.visibility = View.VISIBLE
        mainPresenterImpl.topAnimeMangaView = this
        mainPresenterImpl.getTopAnime(true,activity, page = page++)

    }


    fun getMoreItems() {
        Timber.d("Page  getMoreItems -> $page ")
        mainPresenterImpl.getTopAnime(false,activity, page = page++)

    }


    override fun onDataFetch(topModelList: List<TopModel>) {
        if (progressbar != null)
            progressbar.visibility = View.GONE

        topAdapter.updateList(topModelList)
    }

    override fun errorOccured(error: VolleyError) {
        if (progressbar != null)
            progressbar.visibility = View.GONE
        Timber.d("Volley Error : ${error.message}")

    }


}
