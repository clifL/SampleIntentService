package io.clifl.myintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}