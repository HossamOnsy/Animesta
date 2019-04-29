package com.sam.animesta.presenter

import com.android.volley.VolleyError
import com.sam.animesta.model.TopModel
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel


interface DetailsPresenter {

    fun dataFetched(model:AnimeDetailsModel)
    fun errorOccured(error: VolleyError)

}