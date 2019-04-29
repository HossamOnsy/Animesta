package com.sam.animesta.presenter

import com.android.volley.VolleyError
import com.sam.animesta.model.TopModel


interface MainPresenter {

    fun dataFetched(topModelList: List<TopModel>)
    fun errorOccured(error: VolleyError)

}