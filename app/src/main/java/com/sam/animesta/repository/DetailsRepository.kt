package com.sam.animesta.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sam.animesta.utils.BASE_URL
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel
import com.sam.animesta.presenter.DetailsPresenter
import timber.log.Timber
import javax.inject.Inject


class DetailsRepository @Inject constructor() {

    lateinit var detailsPresenter: DetailsPresenter

    fun getDetails(id:Int,context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/anime/$id"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, AnimeDetailsModel::class.java)

                detailsPresenter.dataFetched(jsonResponse)
                Timber.d("Response jsonResponse ->  $jsonResponse")

            },
            Response.ErrorListener { error ->
                error.printStackTrace();
                Timber.d("Response error ->  $error")
            })

        queue.add(stringRequest)

    }


}