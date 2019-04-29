package com.sam.animesta.presenter

import com.android.volley.VolleyError
import com.sam.animesta.model.TopModel
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel


interface AnimeDetails {

   fun onModelFetched(animeDetailsModel: AnimeDetailsModel)
   fun errorOccured(error: VolleyError)

}