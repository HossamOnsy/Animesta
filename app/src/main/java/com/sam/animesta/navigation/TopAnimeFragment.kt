package com.sam.animesta.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.sam.animesta.R
import com.sam.animesta.adapters.TopAdapter
import com.sam.animesta.di.DaggerActivityComponent
import com.sam.animesta.model.TopModel
import com.sam.animesta.presenter.MainActivityView
import com.sam.animesta.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.fragment_top_anime.*
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
class TopAnimeFragment : Fragment() , MainActivityView {

    @Inject
    lateinit var mainPresenterImpl: MainPresenterImpl

    lateinit var topAdapter :  TopAdapter

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


        topAdapter = TopAdapter(activity,ArrayList())
        rv_top.adapter = topAdapter
        rv_top.layoutManager= GridLayoutManager(activity,2)
        rv_top.setHasFixedSize(false)

        mainPresenterImpl.mainActivityView = this
        mainPresenterImpl.getTopAnime(activity, 1)

    }

    override fun onDataFetch(topModelList: List<TopModel>) {
        topAdapter.updateList(topModelList)
    }

}
