package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.peiflow.ruedasrarasapp.adapters.FirestoreManager
import com.peiflow.ruedasrarasapp.helpers.JsonHelper
import com.peiflow.ruedasrarasapp.helpers.NetworkHelper
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.models.Hint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RuedasRarasApp : Application() {
    private var localDbEventHash: Long = 0
    private var cloudDbEventHash: Long = 0
    private lateinit var localDbEventsData: MutableList<EventData>
    private lateinit var cloudDbEventsData: MutableList<EventData>
    private lateinit var cloudDbRawEventsData: String

    private lateinit var firestoreManager: FirestoreManager

    override fun onCreate() {
        super.onCreate()

        setupDatabase()
        initializeVariables()

        if (!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)

        /*
        * 1.-Check internet connection
        *   2.1.-Internet OK
        *     2.1.1.- Get hash from cloub through a delegate method
        *     2.1.2.- Gel local hash
        *     2.1.3.- Compare hashes
        *     2.1.4.- If hashes are NOT equal
        *         2.4.1.- Download database
        *         2.4.5.- Store downloaded data into local storage
        *   2.2.-Internet KO
        *     2.2.1.- Check your internet collection message
        *     2.2.2.- Use local data
        * */

        //check if local files exists
        val filesExists: Boolean = JsonHelper.checkIfFilesExists(this)

        if (!filesExists)
            JsonHelper.createFiles(this, "", "")

        //Internet connection ok
        if (NetworkHelper.getNetworkAvailability(this)) {
            //get local hash
            localDbEventHash = JsonHelper.getEventsHash(this)
            //get cloud hash
            GlobalScope.launch {
                cloudDbEventHash = FirestoreManager.getSyncEventsHash()
                checkHashes()
            }
        }
    }

    fun setCloudHash(hash: Long) {
        cloudDbEventHash = hash
        Log.d("CLOUD HASH", "$cloudDbEventHash")

        checkHashes()
    }

    private fun initializeVariables() {
        localDbEventsData = mutableListOf()
        cloudDbEventsData = mutableListOf()
        cloudDbRawEventsData = ""
    }

    private fun setupDatabase() {
        firestoreManager = FirestoreManager()
    }


    private fun checkHashes() {
        Log.d(ContentValues.TAG, "LOCAL HASH: $localDbEventHash")
        Log.d(ContentValues.TAG, "CLOUD HASH:  $cloudDbEventHash")

        if (cloudDbEventHash != localDbEventHash) {
            cloudDbEventsData = FirestoreManager.getSyncEventsList()
            JsonHelper.saveHash(this as Application, cloudDbEventHash)
            JsonHelper.saveRawEventsData(this as Application, cloudDbEventsData)
        } else {
            Log.d(ContentValues.TAG, "HASHES IGUALES")
        }
    }
}