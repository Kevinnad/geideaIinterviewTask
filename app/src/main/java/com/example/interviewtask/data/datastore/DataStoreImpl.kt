package com.example.interviewtask.data.datastore

import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.data.model.MainResult
import com.example.interviewtask.database.DataBaseSource
import com.example.interviewtask.network.ResultWrapper
import com.example.interviewtask.network.Services
import com.example.interviewtask.network.handleRequest
import javax.inject.Inject

class DataStoreImpl @Inject constructor(
    val services: Services,
    val dataBaseSource: DataBaseSource
) : DataStore {

    override suspend fun getDataList(pageNumber: Int): ResultWrapper<List<DataModel>> {
        return updateInDB(pageNumber)
    }

    suspend fun fetchForms(formsResult: ResultWrapper<MainResult>, pageNumber: Int): ResultWrapper<List<DataModel>> {

        when (formsResult) {
            is ResultWrapper.Success -> {
                return insertForms(formsResult.value.data,pageNumber)
            }
            is ResultWrapper.NetworkError -> {
                return formsResult
            }
            is ResultWrapper.GenericError -> {
                return formsResult
            }
            else ->
                throw Exception()
        }
    }

    private suspend fun insertForms(dataList: List<DataModel>, pageNumber: Int): ResultWrapper<List<DataModel>> {
        dataBaseSource.insertAllData(dataList,pageNumber)
        return updateInDB(pageNumber)
    }

    suspend private fun updateInDB(pageNumber: Int): ResultWrapper<List<DataModel>> {

        val list = dataBaseSource.getDataList(pageNumber)

        if (list != null && list.size > 0) {
            return handleRequest { list }
        } else {
            return fetchForms(handleRequest { services.getListApi(pageNumber) },pageNumber)
        }
    }

}