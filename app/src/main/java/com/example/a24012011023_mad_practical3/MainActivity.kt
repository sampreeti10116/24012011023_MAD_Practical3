package com.example.a24012011023_mad_practical3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        implicitIntent()
        explicitIntent()
    }

    fun implicitIntent() {
        findViewById<Button>(R.id.btn_browse).setOnClickListener {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(findViewById<EditText>(R.id.editTextText).text.toString())
            ).also {
                startActivity(it)
            }
        }

        val callButton = findViewById<Button>(R.id.btn_call)
        callButton.setOnClickListener {
            val number = findViewById<EditText>(R.id.editTextPhone).text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData("tel:$number".toUri())
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_callLog).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_cam).setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_gallery).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                type = "image/*"
                action = Intent.ACTION_PICK
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_alarm).setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }

    }

    fun explicitIntent() {
        findViewById<Button>(R.id.btn_logIn).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}