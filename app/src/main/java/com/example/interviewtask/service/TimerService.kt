package com.example.interviewtask.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper

class TimerService : Service() {

    private var timercallBack : TimerCallBack? = null
    private var timer = 5;

    private val binder = LocalBinder()

    fun registerCallBack( mTimercallBack : TimerCallBack?){
        timercallBack = mTimercallBack
    }

    inner class LocalBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun startTimer(){
        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            timer = timer.dec()
            timercallBack?.onTime(timer)
            if(timer > 0){
                startTimer()
            }else{
                stopSelf()
            }

        },1000)
    }

    interface TimerCallBack{

        fun onTime(time : Int);
    }
}