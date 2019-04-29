package com.sam.animesta.presenter

import com.android.volley.VolleyError
import com.sam.animesta.model.TopModel


interface TopAnimeMangaView {

   fun onDataFetch(topModelList: List<TopModel>)
   fun errorOccured(error: VolleyError)

}