package com.example.interviewtask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interviewtask.data.model.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract fun formDao() : MainDao
}