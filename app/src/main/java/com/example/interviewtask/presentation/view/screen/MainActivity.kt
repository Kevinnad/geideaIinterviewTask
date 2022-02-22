package com.example.interviewtask.presentation.view.screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.interviewtask.R
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.databinding.ActivityMainBinding
import com.example.interviewtask.presentation.view.adapter.MainAdapter
import com.example.interviewtask.presentation.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observers()
        initView()
    }

    /*Initialize Movie List Adapter*/
    private fun initView() {

        mainAdapter = MainAdapter(object : MainAdapter.AdapterClickListener {
            override fun onItemClicked(dataModel: DataModel) {
                /*Navigate to Movie Detail Screen*/
                startActivity(
                    DetailActivity.getCallingIntent(
                        this@MainActivity,
                        dataModel
                    )
                )
            }

        })
        activityMainBinding.formList.adapter = mainAdapter
    }

    /*Observe Movie List from ViewModel*/
    private fun observers() {

        mainViewModel.dataListLiveData.observe(this, {

            mainAdapter.submitList(it)

        })
    }
}