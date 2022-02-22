package com.example.interviewtask.presentation.view.screen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.interviewtask.R
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.databinding.MainDetailsBinding
import com.example.interviewtask.service.TimerService
import com.example.interviewtask.utils.Constant.MOVIE_MODEL

class DetailActivity : AppCompatActivity(), TimerService.TimerCallBack {

    var dataModel: DataModel? = null
    lateinit var movieDetailsBinding: MainDetailsBinding
    var bound : Boolean = false

    private lateinit var mService: TimerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.main_details)

        getIntentData()
        initView()
        initService()
    }

    companion object {

        fun getCallingIntent(context: Context, dataModel: DataModel): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_MODEL, dataModel)
            return intent
        }
    }

    fun getIntentData() {
        dataModel = intent?.getParcelableExtra<DataModel>(MOVIE_MODEL)
    }

    fun initView() {
        if (dataModel != null) {
            movieDetailsBinding.dataModel = dataModel
        }
    }

    fun initService(){
        Intent(this, TimerService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }


    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as TimerService.LocalBinder
            bound = true

            mService = binder.getService()
            mService.registerCallBack(this@DetailActivity)
            mService.startTimer()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            bound = false
        }

    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        mService.stopSelf()
        mService.registerCallBack(null)

    }

    override fun onTime(time : Int) {
        movieDetailsBinding.tvTimer.text = "$time"
        if(time < 1){
            finish()
        }
    }

}