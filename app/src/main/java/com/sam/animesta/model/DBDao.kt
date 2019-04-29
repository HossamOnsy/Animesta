package com.sam.animesta.model

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DBDao {


    @Query("SELECT *  FROM TopModelData WHERE  type != \"Manga\" AND type !=\"Novel\"")
    fun getAnimeModels() : List<TopModel>

    @Query("SELECT *  FROM TopModelData WHERE type = \"Manga\" OR type =\"Novel\"")
    fun getMangaModels() : List<TopModel>

    @Insert
    fun insert(topModel: TopModel)

}