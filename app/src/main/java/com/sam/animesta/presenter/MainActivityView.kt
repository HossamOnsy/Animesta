package com.sam.animesta.presenter

import com.sam.animesta.model.TopModel


interface MainActivityView {

   fun onDataFetch(topModelList: List<TopModel>)

}