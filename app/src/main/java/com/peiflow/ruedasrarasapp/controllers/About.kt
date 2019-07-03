package com.peiflow.ruedasrarasapp.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.peiflow.ruedasrarasapp.R
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val with: Int = (dm.widthPixels * 0.9).toInt()
        val height: Int = (dm.heightPixels * 0.8).toInt()

        window.setLayout(with, height)

        my_mail.setOnClickListener{
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("p852pa@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Dev contact")
            email.putExtra(Intent.EXTRA_TEXT, android.R.id.message)
            //need this to prompts email client only
            email.type = "message/rfc822"
            startActivity(Intent.createChooser(email, "Seleccione un cliente de email:"))
        }
    }
}
