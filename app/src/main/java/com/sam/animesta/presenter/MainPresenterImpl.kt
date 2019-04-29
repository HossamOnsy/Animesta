package com.sam.animesta.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.android.volley.VolleyError
import com.sam.animesta.AppDatabase
import com.sam.animesta.model.TopModel
import com.sam.animesta.repository.MainRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(var repository: MainRepository) : MainPresenter {


    lateinit var topAnimeMangaView: TopAnimeMangaView
    lateinit var db: AppDatabase
    lateinit var context: Context

    init {
        repository.mainPresenter = this
    }

    fun getTopAnime(context: FragmentActivity?, page: Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java, "TopModelData")
            .fallbackToDestructiveMigration()
            .build()
        val dao = db.dbDao()

        doAsync {
            val t = dao.getAnimeModels()
            uiThread {
                if (t.size > 0) {
                    dataFetched(t)
                } else {
                    repository.getTopAnime(dao, context, page)
                }
            }
        }
    }


    fun getTopManga(context: FragmentActivity?, page: Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java, "TopModelData")
            .fallbackToDestructiveMigration()
            .build()
        val dao = db.dbDao()

        doAsync {
            val t = dao.getMangaModels()
            uiThread {
                if (t.size > 0) {
                    dataFetched(t)
                } else {
                    repository.getTopManga(dao, context, page)
                }
            }
        }
    }


    override fun dataFetched(topModelList: List<TopModel>) {

        topAnimeMangaView.onDataFetch(topModelList)

    }
    override fun errorOccured(error: VolleyError) {
        topAnimeMangaView.errorOccured(error)
    }
}