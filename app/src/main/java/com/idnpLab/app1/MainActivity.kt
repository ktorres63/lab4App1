package com.idnpLab.app1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.idnpLab.app1.R

class MainActivity : AppCompatActivity() {
    private val broad = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                "com.idnpLab.app2.ACTION_SEND" ->{
                    val string = intent?.getStringExtra("com.idnpLab.app2.EXTRA_DATA")
                    Toast.makeText(context,"Message from App2-> ${string}",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter("com.idnpLab.app2.ACTION_SEND")
        registerReceiver(broad,intentFilter)

        val btn = findViewById<AppCompatButton>(R.id.buttonEnviar)
        val etMessage = findViewById<EditText>(R.id.etMensaje)
        btn.setOnClickListener {
            val mssg = etMessage.text.toString()
            Toast.makeText(this,"Message from App1-> ${mssg}",Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broad)

    }
}