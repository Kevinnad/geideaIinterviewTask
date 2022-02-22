package com.example.interviewtask.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.domain.repository.MainRepository
import javax.inject.Inject

class DataListUseCaseImp @Inject constructor(val mainRepository: MainRepository) : DataListUseCase {

/*Use case to load Movie List in the Main screen*/
    override fun getDataList() : LiveData<PagedList<DataModel>> {
        return mainRepository.getDataList()
    }

}