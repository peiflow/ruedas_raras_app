package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class RuedasRarasApp : Application() {

    override fun onCreate() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        super.onCreate()
    }
}