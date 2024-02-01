package kr.co.lion.android32_service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TestService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}