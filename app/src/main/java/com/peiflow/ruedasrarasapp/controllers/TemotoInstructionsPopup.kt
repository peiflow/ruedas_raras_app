package com.peiflow.ruedasrarasapp.controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import com.peiflow.ruedasrarasapp.R

class TemotoInstructionsPopup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temoto_instructions_popup)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val with:Int = (dm.widthPixels*0.9).toInt()
        val height:Int = (dm.heightPixels*0.8).toInt()

        window.setLayout(with, height)
    }
}