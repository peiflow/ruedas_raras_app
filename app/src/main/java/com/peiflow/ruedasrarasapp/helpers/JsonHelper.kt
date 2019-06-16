package com.peiflow.ruedasrarasapp.helpers

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.utils.Constants
import java.io.File
import java.lang.Exception


class JsonHelper {
    companion object {

        fun checkIfFilesExists(app: Application): Boolean {
            var file1: File = app.baseContext.getFileStreamPath(Constants.EVENTS_DATA_FILE_NAME)
            var file2: File = app.baseContext.getFileStreamPath(Constants.EVENTS_HASH_FILE_NAME)

            return file1.exists() && file2.exists()
        }

        fun createFiles(app: Application, hash: String, eventsData: String) {
            app.openFileOutput(Constants.EVENTS_DATA_FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(eventsData.toByteArray())
            }
            app.openFileOutput(Constants.EVENTS_HASH_FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(hash.toByteArray())
            }
        }

        fun getEventsHash(app: Application): Long {
            val json_string: String =
                app.baseContext.openFileInput(Constants.EVENTS_HASH_FILE_NAME).bufferedReader().use { it.readText() }
            if (json_string != "") {
                val parsed = JsonParser().parse(json_string).asLong
                return parsed
            } else {
                return 0
            }
        }

        fun getEventsData(app: Application): MutableList<EventData> {
            var eventsData: MutableList<EventData> = mutableListOf()

            val json_string: String =
                app.baseContext.openFileInput(Constants.EVENTS_DATA_FILE_NAME).bufferedReader().use { it.readText() }
            //val json_string: String = app.assets.open(file_name).bufferedReader().use { it.readText() }
            if (json_string != "") {
                val parsed = JsonParser().parse(json_string).asJsonArray
                for(p in parsed){
                    val event:EventData = Gson().fromJson(p, EventData::class.java)
                    eventsData.add(event)
                }
                /*val parsed = JsonParser().parse(json_string).asJsonObject
                val keys = parsed.keySet()
                for (key in keys) {
                    var eventArray = parsed[key].asJsonObject
                    for (key2 in eventArray.keySet()) {
                        val event: EventData = Gson().fromJson(eventArray[key2], EventData::class.java)
                        eventsData.add(event)
                    }
                }*/
            }
            return eventsData
        }

        fun saveRawEventsData(app: Application, eventsData: MutableList<EventData>) {
            try {
                var serialized = Gson().toJson(eventsData)
                app.baseContext.openFileOutput(Constants.EVENTS_DATA_FILE_NAME, MODE_PRIVATE).write(serialized.toByteArray())
                Log.d("DATA SAVED","Events data json saved. Number of rows: ${eventsData.count()}")
            }catch (ex:Exception){
                Log.e("ERROR","Error saving events data json. $ex")
            }
        }

        fun saveHash(app: Application, hash: Long) {
            try{
            app.baseContext.openFileOutput(Constants.EVENTS_HASH_FILE_NAME, MODE_PRIVATE)
                .write(hash.toString().toByteArray())
                Log.d("DATA SAVED","Hash json saved. Value: ${hash}")
            }catch (ex:Exception){
                Log.e("ERROR","Error saving events hash json. ${ex.toString()}")
            }
        }
    }
}