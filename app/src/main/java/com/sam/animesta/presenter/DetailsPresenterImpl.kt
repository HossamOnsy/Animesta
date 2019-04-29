package com.sam.animesta.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.android.volley.VolleyError
import com.sam.animesta.AppDatabase
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel
import com.sam.animesta.repository.DetailsRepository
import javax.inject.Inject

class DetailsPresenterImpl @Inject constructor(var repository: DetailsRepository) : DetailsPresenter {


    lateinit var animeDetails: AnimeDetails
    lateinit var db: AppDatabase
    lateinit var context: Context

    init {
        repository.detailsPresenter = this
    }


    fun getDetailsModel(id : Int, context: FragmentActivity?) {

        this.context = context!!
        repository.getDetails(id, context)


    }

    override fun dataFetched(model: AnimeDetailsModel) {
        animeDetails.onModelFetched(model)
    }

    override fun errorOccured(error: VolleyError) {
        animeDetails.errorOccured(error)
    }
}