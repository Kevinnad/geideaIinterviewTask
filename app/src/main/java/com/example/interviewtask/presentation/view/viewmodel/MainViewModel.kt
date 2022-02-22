package com.example.interviewtask.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.domain.interactor.DataListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val dataListUseCase: DataListUseCase) : ViewModel() {

     val dataListLiveData : LiveData<PagedList<DataModel>>
         get() {
            return dataListUseCase.getDataList()
         }

}