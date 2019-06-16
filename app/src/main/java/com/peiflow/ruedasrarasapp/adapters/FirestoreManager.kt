package com.peiflow. ruedasrarasapp.adapters

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.*
import com.peiflow.ruedasrarasapp.MainActivity
import com.peiflow.ruedasrarasapp.controllers.RuedasRarasApp
import com.peiflow.ruedasrarasapp.models.EventData

class FirestoreManager {
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val eventsColl = db.collection("events")
    private val hashColl= db.collection("events_hash")
    lateinit var  rEventsList:MutableList<EventData>

    constructor(){
        rEventsList = mutableListOf()
    }

    fun getEventsByDate(context: Context):MutableList<EventData>{
        var eventsList:MutableList<EventData> = mutableListOf()
        eventsColl.orderBy("dateTime").get().addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
            override fun onComplete(p0: Task<QuerySnapshot>) {
                if(p0.result!!.metadata.isFromCache)
                    Log.d("tag", "LOCAL CACHE")
                else
                    Log.d("tag", "SERVER")

                if(p0.isSuccessful){
                    for(item in p0.getResult()!!){
                        var event:EventData = item.toObject(EventData::class.java)
                        eventsList.add(event)
                    }
                    (context as MainActivity).setupRecyclerView(eventsList)
                }
            }
        })
        return eventsList
    }

    fun getEventsHash(context: Context):Long{
        var hash:Long = 0

        hashColl.get().addOnCompleteListener(object: OnCompleteListener<QuerySnapshot>{
            override fun onComplete(p0: Task<QuerySnapshot>) {
                if(p0.isSuccessful){
                    if(p0.result!!.size() > 0){
                        hash = p0.result!!.first().data["hash"] as Long
                    }
                    (context as RuedasRarasApp).setCloudHash(hash)
                }
            }
        })
        return  hash
    }
    fun getSyncEventsHash():Long{
        var hash:Long = 0L
        val task: Task<QuerySnapshot> = hashColl.get()
        val snap:QuerySnapshot = Tasks.await(task)
        if(snap!=null)
            hash = snap.documents[0].data!!["hash"] as Long
        Log.d("ASYNC HASH", "$hash")
        return  hash
    }

    fun getSyncEventsList():MutableList<EventData>{
        var eventsList:MutableList<EventData> = mutableListOf()
        val task =eventsColl.orderBy("dateTime").get()
        val snap:QuerySnapshot = Tasks.await(task)
        if(snap!=null){
            for(item in snap!!){
                var event:EventData = item.toObject(EventData::class.java)
                eventsList.add(event)
            }
        }

        Log.d("ASYNC EVENT DATA COUNT", "${eventsList.count()}")
        return eventsList
    }
}