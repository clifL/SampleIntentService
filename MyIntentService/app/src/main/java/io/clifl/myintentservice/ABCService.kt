package io.clifl.myintentservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

// The main different between a service and an intentservice is such that an intentservice will automatically run on a separate thread whereby
// a normal service will run on the main thread by default
// Thus, we should always start a separate thread when creating such as service.
class ABCService : Service() {

    var job: Job? = null
    val TAG = "MyService"

    init {
        Log.d(TAG, "A normal service is running")
    }

    // This is only required if we have multiple clients want to connect to our service at the same time.
    // We can just return null if we do not need this feature.
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // By starting a separate thread, the things being done in here will not hang the system
        Thread {
            Log.d(TAG, Thread.currentThread().name)
            val dataString = intent?.getStringExtra("EXTRA_DATA")
            dataString?.let {
                Log.d(TAG, dataString)
            }
        }.start()


        // Either the one below or the one on top will do, just place both here for references
        // Create a background coroutine that runs on a background thread
        var job = GlobalScope.launch {
            Log.d(TAG, Thread.currentThread().name)
            val dataString = intent?.getStringExtra("EXTRA_DATA")
            dataString?.let {
                Log.d(TAG, dataString)
            }

            /*while(true) {
                Log.d(TAG, "still exist")

            }*/
        }

        //job?.cancel()

        // If the code below is uncommented, it will hangs the system.
        // However if is placed inside the Thread curly brackets above, it wont hang the system.
        //while(true) {}
        return START_STICKY
    }

    // An intentservice will stop automatically once it complete its job (unless we placed while loops)
    // However, a normal service will keep running forever in background, unless we manually calls the stopservice().



}