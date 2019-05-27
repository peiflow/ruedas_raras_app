package com.peiflow.ruedasrarasapp.helpers

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.utils.Constants
import java.io.File


class JsonHelper {
    companion object {

        fun checkIfFilesExists(app: Application):Boolean
        {
            var file1: File = app.baseContext.getFileStreamPath(Constants.EVENTS_DATA_FILE_NAME)
            var file2: File = app.baseContext.getFileStreamPath(Constants.EVENTS_HASH_FILE_NAME)

            return file1.exists() && file2.exists()
        }

        fun createFiles(app: Application, hash:String, eventsData: String)
        {
            app.openFileOutput(Constants.EVENTS_DATA_FILE_NAME, Context.MODE_PRIVATE).use{
                it.write(eventsData.toByteArray())
            }
            app.openFileOutput(Constants.EVENTS_HASH_FILE_NAME, Context.MODE_PRIVATE).use{
                it.write(hash.toByteArray())
            }
        }

        fun getEventsHash(app: Application): String {
            var hash = ""
            val file_name = "eventsHash.json"

            //val json_string: String = app.assets.open(file_name).bufferedReader().use { it.readText() }
            val json_string:String = app.baseContext.openFileInput(Constants.EVENTS_HASH_FILE_NAME).bufferedReader().use { it.readText() }
            if (json_string != "") {
                val parsed = JsonParser().parse(json_string).asJsonObject
                val eventHash = parsed.get("eventsHash").asString
                hash = eventHash
            }

            return hash
        }
        fun getEventsData(app: Application): MutableList<EventData> {
            var eventsData:MutableList<EventData> = mutableListOf()
            val file_name = "eventsData.json"

            val json_string:String = app.baseContext.openFileInput(Constants.EVENTS_DATA_FILE_NAME).bufferedReader().use { it.readText() }
            //val json_string: String = app.assets.open(file_name).bufferedReader().use { it.readText() }
            if (json_string != "") {
                val parsed = JsonParser().parse(json_string).asJsonObject
                val keys = parsed.keySet()
                for (key in keys)
                {
                    var eventArray = parsed[key].asJsonObject
                    for(key2 in eventArray.keySet())
                    {
                        val event: EventData = Gson().fromJson(eventArray[key2], EventData::class.java)
                        eventsData.add(event)

                    }
                }
            }
            return mutableListOf()
        }
        fun saveRawEventsData(app: Application, rawEventsData: String)
        {

        }
    }
}