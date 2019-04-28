package com.sam.animesta

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sam.animesta.model.DBDao
import com.sam.animesta.model.TopModel
import com.sam.animesta.model.TopTestModel

@Database(entities = arrayOf(TopModel::class,TopTestModel::class), version = 1,exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DBDao
}