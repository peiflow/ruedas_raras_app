package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import android.content.ContentValues
import android.util.Log
import com.peiflow.ruedasrarasapp.adapters.FirestoreManager
import com.peiflow.ruedasrarasapp.helpers.JsonHelper
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.models.Hint

class RuedasRarasApp : Application() {
    private var localDbEventHash: Long = 0
    private var cloudDbEventHash: Long = 0
    private lateinit var localDbEventsData: MutableList<EventData>
    private lateinit var cloudDbEventsData: MutableList<EventData>
    private lateinit var cloudDbRawEventsData: String

    private lateinit var firestoreManager: FirestoreManager

    override fun onCreate() {
        super.onCreate()
        //initialize variables
        initializeVariables()
        setupDatabase()

        //Check hints file
        if (!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)

        //check if local files exists
        val filesExists: Boolean = JsonHelper.checkIfFilesExists(this)

        if (!filesExists)
            JsonHelper.createFiles(this, "", "")

        //Gets local and cloud hashes
        cloudDbEventHash = firestoreManager.getEventsHash(this.applicationContext)
        JsonHelper.getEventsHash(this)

        checkHashes()


        /*
        * IF eventsHashDbCollection != eventsHashLocalCollection
        *   eventsList = eventsHashDbCollection.get()
        * ELSE
        *   eventsList = eventsHashLocalCollection.get()
        */
    }

    private fun initializeVariables() {
        localDbEventsData = mutableListOf()
        cloudDbEventsData = mutableListOf()
    }

    private fun setupDatabase() {
        firestoreManager = FirestoreManager()
    }


    private fun checkHashes() {
        Log.d(ContentValues.TAG, "LOCAL HASH: $localDbEventHash")
        Log.d(ContentValues.TAG, "CLOUD HASH:  $cloudDbEventHash")

        if (cloudDbEventHash != localDbEventHash) {
            JsonHelper.saveHash(this as Application, cloudDbEventHash)
            //JsonHelper.saveRawEventsData(this as Application, cloudDbRawEventsData)
        } else {
            Log.d(ContentValues.TAG, "HASHES IGUALES")
        }
    }
}