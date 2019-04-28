package com.sam.animesta.presenter

import com.sam.animesta.AppDatabase
import com.sam.animesta.model.TopModel


interface MainPresenter {

    fun dataFetched(jsonResponse: List<TopModel>);

}