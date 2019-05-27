package com.peiflow.ruedasrarasapp.controllers

import android.app.Application
import android.content.ContentValues
import android.util.Log
import com.google.firebase.database.*
import com.peiflow.ruedasrarasapp.helpers.JsonHelper
import com.peiflow.ruedasrarasapp.models.EventData

class RuedasRarasApp : Application() {
    private lateinit var localDbEventHash: String
    private lateinit var cloudDbEventHash: String
    private lateinit var localDbEventsData: MutableList<EventData>
    private lateinit var cloudDbEventsData: MutableList<EventData>
    private lateinit var cloudDbRawEventsData: String

    private lateinit var eventsDbRef: DatabaseReference
    private lateinit var hashDbRef: DatabaseReference

    override fun onCreate() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        super.onCreate()

        //initialize variables
        initializeVariables()

        //setup database
        val dbInstance = FirebaseDatabase.getInstance()
        eventsDbRef = dbInstance.getReference("events")
        hashDbRef = dbInstance.getReference("eventsHash")
        hashDbRef.keepSynced(true)
        eventsDbRef.keepSynced(true)

        //check if local files exists
        val filesExists: Boolean = JsonHelper.checkIfFilesExists(this)

        /*if (!filesExists) {
            //Crear archivos
            getCloudEventsHash()
            getCloudRawEventsData()
            JsonHelper.createFiles(this, cloudDbEventHash, cloudDbRawEventsData)
        } else {
            localDbEventHash = JsonHelper.getEventsHash(this)
            localDbEventsData = JsonHelper.getEventsData(this)

            checkHashes()
        }*/

        getCloudEventsHash()
        getCloudRawEventsData()

        if (!filesExists)
            JsonHelper.createFiles(this, "", "")

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
        localDbEventHash = ""
        localDbEventsData = mutableListOf()
        cloudDbEventHash = ""
        cloudDbRawEventsData = ""
        cloudDbEventsData = mutableListOf()
    }

    private fun getCloudEventsHash() {
        hashDbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled: Failed to read message --> ${p0.toException()}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                cloudDbEventHash = p0.value.toString()
            }
        })
    }

    private fun getCloudRawEventsData() {
        eventsDbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    cloudDbRawEventsData = p0.value.toString()
                    JsonHelper.saveRawEventsData(this as Application, cloudDbRawEventsData)
                }
            }
        })
    }

    private fun checkHashes() {
        Log.d(ContentValues.TAG, "LOCAL HASH: $localDbEventHash")
        Log.d(ContentValues.TAG, "CLOUD HASH:  $cloudDbEventHash")


        if (cloudDbEventHash != localDbEventHash) {
            JsonHelper.saveRawEventsData(this as Application, cloudDbRawEventsData)
        } else {
            Log.d(ContentValues.TAG, "HASHES IGUALES")
        }
        
            if(!Hint.checkIfFileExists(this.applicationContext))
            Hint.createFile(this.applicationContext)
        else
            Hint.getHints(this.applicationContext)
    }
}