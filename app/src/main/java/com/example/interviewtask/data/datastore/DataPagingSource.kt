package com.example.interviewtask.data.datastore

import androidx.paging.PageKeyedDataSource
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.network.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataPagingSource(val dataStore: DataStore) :
    PageKeyedDataSource<Int, DataModel>() {

    private val STARTING_PAGE_INDEX = 1
    private val STARTING_INITIAL_PAGE = 0
    private val STARTING_NEXT_PAGE = 2


    private suspend fun loadData(loadSize: Int, pageNumber: Int): ResultWrapper<List<DataModel>> {
        val response = dataStore.getDataList(pageNumber)

        return response
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataModel>
    ) {
        GlobalScope.launch(Dispatchers.IO) {

            val result = loadData(params.requestedLoadSize, STARTING_PAGE_INDEX)

            handleLoadResult(result) { groupedListResult ->
                if (groupedListResult != null) {
                    var totalCount = groupedListResult.size.toInt()


                    callback.onResult(groupedListResult, STARTING_INITIAL_PAGE, totalCount, null, STARTING_NEXT_PAGE)
                } else {
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataModel>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataModel>) {
        GlobalScope.launch(Dispatchers.IO) {

            val result = loadData(params.requestedLoadSize, params.key)

            handleLoadResult(result) { groupedListResult ->
                if (groupedListResult != null) {
                    callback.onResult(groupedListResult, params.key + 1)
                } else {
                }
            }
        }
    }

    /* Handle Movie result from Data Store and invoke Page callback. Error handling can also be achieved */
    private fun handleLoadResult(
        result: ResultWrapper<List<DataModel>>,
        callback: (groupedListResult: List<DataModel>?) -> Unit
    ) {
        when (result) {
            is ResultWrapper.Success -> {
                callback.invoke(result.value)
            }
            is ResultWrapper.NetworkError -> {
                /*Error handling*/
            }
            is ResultWrapper.GenericError -> {
                /*Error handling*/
            }
            else -> {
                /*Error handling*/
            }

        }

    }
}