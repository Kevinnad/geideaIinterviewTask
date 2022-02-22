package com.example.interviewtask.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.interviewtask.data.model.DataModel

interface DataListUseCase {

    fun getDataList() : LiveData<PagedList<DataModel>>
}