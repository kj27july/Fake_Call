package com.call.safecall.ui

import android.Manifest.permission.CALL_PHONE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.call.safecall.R
import android.net.Uri
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var callBtn: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        callBtn = findViewById(R.id.call_button)

        if (!isCallPermissionGranted())
            ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), 123)
        call()
    }

    private fun call() {
        callBtn.setOnClickListener {
            val nm = name.text.toString()
            val callIntent = Intent(ACTION_CALL)
            callIntent.data = Uri.parse("tel:$nm")
            startActivity(callIntent)
        }
    }

    private fun isCallPermissionGranted(): Boolean {
        return isPermissionGranted(CALL_PHONE)
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this, permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}