package com.serviceApp.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainViewModel  :ViewModel(){
    private val _time = MutableStateFlow("00:00:00")
    val timeShow: StateFlow<String> = _time

    fun getTimeStringFromDouble(time: Double) {
        val result = time.roundToInt()
        val hour = result % 86400 / 3600
        val minute = result % 86400 % 3600 / 60
        val second = result % 86400 % 3600 % 60
        _time.value =  String.format("%02d:%02d:%02d", hour, minute, second)


    }


}