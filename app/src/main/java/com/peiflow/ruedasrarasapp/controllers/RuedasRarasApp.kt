package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.peiflow.ruedasrarasapp.models.Hint
import com.google.firebase.firestore.FirebaseFirestoreSettings



class RuedasRarasApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        FirebaseFirestore.getInstance().firestoreSettings = settings

        if(!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)
    }
}