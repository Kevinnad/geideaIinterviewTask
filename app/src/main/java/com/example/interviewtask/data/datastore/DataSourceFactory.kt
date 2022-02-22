package com.example.interviewtask.data.datastore

import androidx.paging.DataSource
import com.example.interviewtask.data.model.DataModel

class DataSourceFactory constructor(val dataStore: DataStore): DataSource.Factory<Int, DataModel>(){

    lateinit var dataPagingSource: DataPagingSource

    override fun create(): DataSource<Int, DataModel> {
        dataPagingSource = DataPagingSource(dataStore)
        return  dataPagingSource
    }

}