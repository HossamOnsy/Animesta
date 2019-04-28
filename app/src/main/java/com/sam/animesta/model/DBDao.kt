package com.sam.animesta.model

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DBDao {

//    @Query("DELETE  FROM MovieDetailModel Where id = :id and favorite = :favorite ")
//    fun deleteModel(id: Int,favorite:Boolean)


    @Query("SELECT *  FROM TopModelData")
    fun getAllModels() : List<TopModel>


//    @Insert
//    fun insert(vararg topAnimeResponseModel: TopAnimeResponseModel)

    @Insert
    fun insert(topModel: TopModel)

}