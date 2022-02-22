package com.example.interviewtask.database

import com.example.interviewtask.data.model.DataModel
import javax.inject.Inject

class DataBaseSource @Inject constructor(val mainDataBase: MainDataBase) {

    /* DATABASE interactor */

    suspend fun insertAllData(dataModelList: List<DataModel>, page : Int){
        for(model in dataModelList){
            model.page = page
        }
        mainDataBase.formDao().insertAll(dataModelList)
    }

    suspend fun getDataList(page: Int) : List<DataModel>?{
        return mainDataBase.formDao().getAll(page)
    }
}