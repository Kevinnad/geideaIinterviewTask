package com.example.interviewtask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.interviewtask.data.model.DataModel

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dataModelList: List<DataModel>)

    @Query("SELECT * FROM datamodel WHERE page IN(:page) ORDER BY lastName ")
    suspend fun getAll(page : Int) : List<DataModel>?
}