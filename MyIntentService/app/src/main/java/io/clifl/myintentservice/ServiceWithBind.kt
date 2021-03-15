package io.clifl.myintentservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class ServiceWithBind : Service() {
    companion object {
        private const val TAG = "ServiceWithBind"
    }

    private val parentJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Default + parentJob)
    private val mBinder: IBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        val service: ServiceWithBind
            get() = this@ServiceWithBind
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var count = 0
        serviceScope.launch {
            delay(1000)
            //call some method and DO SOMETHING
            Log.d(TAG, "ServiceWithBind started")
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestory()")
        super.onDestroy()
        parentJob.cancel()
    }


}