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
import com.sam.animesta.model.TopModel
import com.sam.animesta.presenter.MainPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.util.*
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
            Response.ErrorListener { error -> Timber.d("Testing anime : ${error.message}") })
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
            Response.ErrorListener { error -> Timber.d("Testing manga : ${error.message}") })
        queue.add(stringRequest)

    }


}