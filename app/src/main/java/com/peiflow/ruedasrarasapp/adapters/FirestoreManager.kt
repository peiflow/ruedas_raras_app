package com.peiflow.ruedasrarasapp.adapters

import android.content.Context
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.peiflow.ruedasrarasapp.MainActivity
import com.peiflow.ruedasrarasapp.models.EventData

class FirestoreManager {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val coll = db.collection("events")

    fun getEventsByDate(context: Context):MutableList<EventData>{
        var eventsList:MutableList<EventData> = mutableListOf()
        coll.orderBy("dateTime").get().addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
            override fun onComplete(p0: Task<QuerySnapshot>) {
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
}