package com.sam.animesta.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.android.volley.VolleyError
import com.sam.animesta.AppDatabase
import com.sam.animesta.R
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

    fun getTopAnime(firstTime : Boolean,context: FragmentActivity?,  page: Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java, context.getString(R.string.DatabaseName))
            .fallbackToDestructiveMigration()
            .build()
        val dao = db.dbDao()





        if(firstTime) {
            doAsync {
                val t = dao.getAnimeModels()
                uiThread {
                    if (t.size > 0) {
                        dataFetched(t)
                        repository.getTopAnime(dao, context, page+1, ArrayList<TopModel>())
                    } else {
                        repository.getTopAnime(dao, context, page, ArrayList<TopModel>())
                    }
                }
            }
        }else{
            repository.getTopAnime(dao, context, page,ArrayList<TopModel>())
        }
    }


    fun getTopManga(context: FragmentActivity?, page: Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java,  context.getString(R.string.DatabaseName))
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
    fun getsearchResults(query : String ,context: FragmentActivity?, page: Int) {

                this.context = context!!
                repository.getSearchResults(query, context, page)


    }

    override fun dataFetched(topModelList: List<TopModel>) {

        topAnimeMangaView.onDataFetch(topModelList)

    }
    override fun errorOccured(error: VolleyError) {
        topAnimeMangaView.errorOccured(error)
    }
}