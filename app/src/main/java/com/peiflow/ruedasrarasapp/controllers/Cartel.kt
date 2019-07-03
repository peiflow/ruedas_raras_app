package com.peiflow.ruedasrarasapp.controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peiflow.ruedasrarasapp.R
import kotlinx.android.synthetic.main.activity_cartel.*

class Cartel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartel)

        pv_cartel.setImageResource(R.drawable.cartel_concencentracion)
    }
}