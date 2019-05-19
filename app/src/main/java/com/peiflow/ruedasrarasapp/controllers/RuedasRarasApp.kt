package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase
import com.peiflow.ruedasrarasapp.models.Hint

class RuedasRarasApp : Application() {

    override fun onCreate() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        super.onCreate()


        if(!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)
    }
}