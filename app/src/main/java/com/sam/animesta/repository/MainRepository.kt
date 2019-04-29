package com.sam.animesta.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sam.animesta.model.DBDao
import com.sam.animesta.utils.BASE_URL
import com.sam.animesta.model.TopAnimeResponseModel
import com.sam.animesta.model.detailedmodel.AnimeDetailsModel
import com.sam.animesta.presenter.MainPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import javax.inject.Inject


class MainRepository @Inject constructor() {

    lateinit var mainPresenter : MainPresenter


    fun getTopAnime(dao:DBDao,context: Context,page:Int) {
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/top/anime/$page"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, TopAnimeResponseModel::class.java)
                for (test  in 0..jsonResponse.top.size-1) {
                    val x = jsonResponse.top.get(test)
                    doAsync {
                        dao.insert(x)
                        uiThread {
                        }
                    }
                }
                mainPresenter.dataFetched(jsonResponse.top)
            },
            Response.ErrorListener { error -> mainPresenter.errorOccured(error) })
        queue.add(stringRequest)

    }

    fun getTopManga(dao:DBDao,context: Context,page:Int) {
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/top/manga/$page"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, TopAnimeResponseModel::class.java)
                for (test  in 0..jsonResponse.top.size-1) {
                    val x = jsonResponse.top.get(test)
                    doAsync {
                        dao.insert(x)
                        uiThread {
                        }
                    }
                }
                mainPresenter.dataFetched(jsonResponse.top)
            },
            Response.ErrorListener { error -> mainPresenter.errorOccured(error) })
        queue.add(stringRequest)

    }
    fun getSearchResults(query:String,context: Context,page:Int) {
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/search/anime?q=${query}&page=$page"



        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, TopAnimeResponseModel::class.java)


                  for(i in jsonResponse.results)
                      Timber.d("Title ->  $i")

                mainPresenter.dataFetched(jsonResponse.results)
            },
            Response.ErrorListener { error ->
                error.printStackTrace();
                Timber.d("Response error ->  $error")
            })

        queue.add(stringRequest)

    }
    fun getDetails(id:String,context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "$BASE_URL/v3/anime/$id"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                var gson = Gson()
                var jsonResponse = gson.fromJson(response, AnimeDetailsModel::class.java)


            },
            Response.ErrorListener { error ->
                error.printStackTrace();
                Timber.d("Response error ->  $error")
            })

        queue.add(stringRequest)

    }


}