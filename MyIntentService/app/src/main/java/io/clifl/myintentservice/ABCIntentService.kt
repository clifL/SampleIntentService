package io.clifl.myintentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

// Note that intent service will not support multi threading, to use multi threading, you will have to use a normal service
class ABCIntentService : IntentService("ABCIntentService") {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: ABCIntentService
        var isRunning = false

        fun stopService() {
            Log.d("ABCIntentService", "Service is stopping...")
            isRunning = false
            instance.stopSelf()
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        try {
            isRunning = true
            while(isRunning) {
                Log.d("ABCIntentService", "Service is running...")
                Log.d("ABCIntentService", Thread.currentThread().name)
                Thread.sleep((1000))
            }
        }
        catch(e: InterruptedException) {
            Log.d("ABCIntentService", "Ran to catch block")
            //Thread.currentThread().interrupt()
        }
    }


}