package com.example.interviewtask.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.interviewtask.data.model.DataModel

interface MainRepository {
/*Get Paged Movie List from */
     fun getDataList() : LiveData<PagedList<DataModel>>

}