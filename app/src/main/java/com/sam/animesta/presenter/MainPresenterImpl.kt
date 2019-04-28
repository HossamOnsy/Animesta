package com.sam.animesta.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.sam.animesta.AppDatabase
import com.sam.animesta.model.TopModel
import com.sam.animesta.repository.MainRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainPresenterImpl  @Inject constructor(var repository: MainRepository) : MainPresenter{

    lateinit var mainActivityView : MainActivityView
    lateinit var db : AppDatabase
    lateinit var context : Context

    override fun dataFetched(topModelList: List<TopModel>) {

        Timber.d("TestingTesting ")
        mainActivityView.onDataFetch(topModelList)

    }

    init {
        repository.mainPresenter=this;
    }

    fun getTopAnime(context: FragmentActivity?, page:Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java, "TopModelData")
            .fallbackToDestructiveMigration()

            .build()
        var dao = db.dbDao()

        doAsync{
            var t = dao.getAnimeModels()
            uiThread {
                Timber.v("Title Size -> ${t.size}")
                for (i in t){
                    Timber.v("Title -> ${i.rank}   rank  ->  ${i.title}")
                }

                if(t.size>0){
//                    var temo = ArrayList<TopModel>(t)
//                    Collections.reverse(temo);
                    dataFetched(t)
                }else{
                    repository.getTopAnime(dao,context,page)
                }
            }
        }
    }


    fun getTopManga(context: FragmentActivity?, page:Int) {
        this.context = context!!
        db = Room.databaseBuilder(context, AppDatabase::class.java, "TopModelData")
            .fallbackToDestructiveMigration()

            .build()
        var dao = db.dbDao()

        doAsync{
            var t = dao.getMangaModels()
            uiThread {
                Timber.v("Title Size -> ${t.size}")
                for (i in t){
                    Timber.v("Title -> ${i.rank}   rank  ->  ${i.title}")
                }

                if(t.size>0){
//                    var temo = ArrayList<TopModel>(t)
//                    Collections.reverse(temo);
                    dataFetched(t)
                }else{
                    repository.getTopManga(dao,context,page)
                }
            }
        }
    }


}