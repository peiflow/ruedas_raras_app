package com.peiflow.ruedasrarasapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.peiflow.ruedasrarasapp.models.EventData


class DatabaseManager {

    var dbRef: DatabaseReference

    constructor() {
        dbRef = FirebaseDatabase.getInstance().getReference("events")
    }

    fun ReadDatabase(eventsList: MutableList<EventData>) {
        eventsList.clear()
        //TODO: Sort elements by dateTime
        val eventsListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e(TAG, "onCancelled: Failed to read message --> ${p0.toException()}")
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (childSnapshot in dataSnapshot.children) {
                        val evt = childSnapshot.getValue(EventData::class.java)
                        if (evt != null) {
                            eventsList.add(evt)
                        }
                    }
                }
            }
        }

        dbRef.addValueEventListener(eventsListener)
    }
}