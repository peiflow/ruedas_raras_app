package com.peiflow. ruedasrarasapp.adapters

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.peiflow.ruedasrarasapp.MainActivity
import com.peiflow.ruedasrarasapp.models.EventData

class FirestoreManager {

    val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    val eventsColl = db.collection("events")
    val hashColl= db.collection("hash")

    constructor(){
    }

    fun getEventsByDate(context: Context):MutableList<EventData>{
        var eventsList:MutableList<EventData> = mutableListOf()
        eventsColl.orderBy("dateTime").get().addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
            override fun onComplete(p0: Task<QuerySnapshot>) {
                if(p0.isSuccessful){
                    Log.d("HASH CODE", "Data hash: "+ p0.result.hashCode())
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

    fun getEventsHash(context: Context):Int{
        var hash:Int = 0

        hashColl.get().addOnCompleteListener(object: OnCompleteListener<QuerySnapshot>{
            override fun onComplete(p0: Task<QuerySnapshot>) {
                if(p0.isSuccessful){
                    Log.d("HASH CODE", "Db hash: "+ p0.result.hashCode())
                    if(p0 != null && p0.result!!.size() > 0){
                        hash = p0.result as Int
                    }
                }
            }
        })

        return hash
    }
}