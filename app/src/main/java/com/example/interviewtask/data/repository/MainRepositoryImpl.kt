package com.example.interviewtask.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.interviewtask.data.datastore.DataSourceFactory
import com.example.interviewtask.data.datastore.DataStore
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val dataStore: DataStore) : MainRepository {

    private val MOVIE_PAGE_SIZE = 20
    private val PREFETCH_DISTANCE = 15


    override fun getDataList(): LiveData<PagedList<DataModel>> {

        val movieDataSourceFactory = DataSourceFactory(dataStore)

        val config = PagedList.Config.Builder()
            .setPageSize(MOVIE_PAGE_SIZE)
            .setInitialLoadSizeHint(MOVIE_PAGE_SIZE)
            .setEnablePlaceholders(true)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .build()

        return LivePagedListBuilder(movieDataSourceFactory, config).build()
    }


}