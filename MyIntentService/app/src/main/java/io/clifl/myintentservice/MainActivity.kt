package io.clifl.myintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // IntentService
        buttonStart.setOnClickListener {
            Intent(this, ABCIntentService::class.java).also {
                startService(it)
                textViewService.text = "Service running"
            }
        }


        buttonStop.setOnClickListener {
            ABCIntentService.stopService()
            textViewService.text = "Service stopped"
        }

        // Normal Service
        buttonStartService.setOnClickListener {
            Log.d("MyService", Thread.currentThread().name)
            Intent(this, ABCService::class.java).also {
                startService(it)
                textViewService.text = "A normal service running"
            }
        }

        buttonStopService.setOnClickListener {
            Intent(this, ABCService::class.java).also {
                // Instead of using companion object (singleton), this is also way to stop a service
                stopService(it)
                Log.d("MyService", "A normal service is stopped")
                textViewService.text = "A normal service stopped"
            }
        }

        buttonSendService.setOnClickListener {
            Intent(this, ABCService::class.java).also {
                // Instead of using companion object (singleton), this is also way to stop a service
                val dataString = editTextDataString.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                // Even though our service is still running, it wont restart it, it will just calls onStartCommand and pass the intent over to the service.
                startService(it)
            }
        }


    }
}