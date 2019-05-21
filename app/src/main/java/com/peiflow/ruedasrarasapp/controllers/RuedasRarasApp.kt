package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import com.peiflow.ruedasrarasapp.models.Hint

class RuedasRarasApp : Application() {

    override fun onCreate() {
        super.onCreate()


        if(!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)
    }
}