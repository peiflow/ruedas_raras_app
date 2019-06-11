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