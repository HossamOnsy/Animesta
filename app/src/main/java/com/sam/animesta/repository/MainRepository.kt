package com.sam.animesta.repository

import android.content.Context
import android.util.Log
import androidx.room.Dao
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sam.animesta.AppDatabase
import com.sam.animesta.model.DBDao
import com.sam.animesta.utils.BASE_URL
import com.sam.animesta.model.TopAnimeResponseModel
import com.sam.animesta.presenter.MainPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject



class MainRepository @Inject constructor() {

    lateinit var mainPresenter : MainPresenter


    fun getTopAnime( dao:DBDao,context: Context,page:Int) {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/top/anime/1"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, TopAnimeResponseModel::class.java)

//                longInfo(response)
                var i =0;
//                Timber.d("jsonResponse >- ${response}")
                Log.v("responseresponse","response -> " + response)


                for (test  in 0..jsonResponse.top.size-1){
                    Log.v("responseresponse","response -> " + jsonResponse.top.get(test))
                    val x = jsonResponse.top.get(test)

//                    x.id = i+1
//                    i = i + 1

                    dao.insert(x)


                }


//                mainPresenter.dataFetched(jsonResponse.top)
                //TODO Call Interface of Presenter

            },
            Response.ErrorListener { error -> Timber.d("Testing : ${error.message}") })

// Add the request to the RequestQueue.
        queue.add(stringRequest)


    }

    fun longInfo(str: String) {
        if (str.length > 4000) {
            Log.v("responseresponse", str)
            longInfo(str.substring(4000))
        } else
            Log.v("responseresponse", str)
    }
}