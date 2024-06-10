package com.serviceApp.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import java.util.*


class MyService : Service() {
    private lateinit var player : MediaPlayer
    private val timer = Timer()

    override fun onStartCommand (init : Intent, flag : Int, startId:Int): Int{
        player= MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        player.isLooping = true
        player.start()

        val time = init.getDoubleExtra("start", 0.0)
        timer.scheduleAtFixedRate(TimeTask(time),0,1000)

        return START_STICKY
    }

    private inner class TimeTask(private var time : Double) : TimerTask()
    {
        override  fun run(){
            val init = Intent("update")
            time ++
            init.putExtra("start", time)
            sendBroadcast(init)
        }

    }

    override fun onDestroy () {
        super.onDestroy()
        player.stop()
        timer.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

}