package com.sam.animesta.navigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
import kotlinx.android.synthetic.main.fragment_search.*
import timber.log.Timber
import javax.inject.Inject


class SearchFragment : Fragment(), SearchView.OnQueryTextListener, TopAnimeMangaView {

    @Inject
    lateinit var mainpresenter: MainPresenterImpl

    lateinit var topAdapter :  TopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var component = DaggerActivityComponent.create()
        component.inject(this)


        topAdapter = TopAdapter("Anime",activity,ArrayList())
        rv_search.adapter = topAdapter
        rv_search.layoutManager= LinearLayoutManager(activity)
        rv_search.setHasFixedSize(false)
        sv_search.setOnQueryTextListener(this)

        mainpresenter.topAnimeMangaView = this

    }

    override fun errorOccured(error: VolleyError) {
        if(progressbar!=null)
            progressbar.visibility = View.GONE

        Timber.d("Volley Error : ${error.message}")


    }

    override fun onDataFetch(topModelList: List<TopModel>) {
        if(progressbar!=null)
            progressbar.visibility = View.GONE

        topAdapter.updateList(topModelList)

    }


    override fun onQueryTextChange(newText: String?): Boolean {


        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query != null) {
            topAdapter.clearList()
            progressbar.visibility = View.VISIBLE
            mainpresenter.getsearchResults(query, activity, 1)
        }


        return true
    }

}

