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
import javax.inject.Inject

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
            .allowMainThreadQueries()
            .build()
        var dao = db.dbDao()

        doAsync{
            var t = dao.getAllModels()
            uiThread {
                Timber.v("Title Size -> ${t.size}")
                for (i in t){
                    Timber.v("Title -> ${i.title}")
                }

                if(t.size>0){
                    dataFetched(t)
                }else{
                    repository.getTopAnime(dao,context,page)
                }
            }
        }
    }


}