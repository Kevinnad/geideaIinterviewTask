package com.example.interviewtask.data.datastore

import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.network.ResultWrapper

interface DataStore {

    suspend fun getDataList(pageNumber: Int): ResultWrapper<List<DataModel>>
}