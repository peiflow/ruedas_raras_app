package com.peiflow.ruedasrarasapp.models

import android.content.Context
import android.nfc.Tag
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.peiflow.ruedasrarasapp.utils.Constants
import java.io.File

class Hint {
    var hint: String = ""

    constructor(hint: String) {
        this.hint = hint
    }

    companion object {
        fun checkIfFileExists(context: Context): Boolean {
            var hintsFile: File = context.applicationContext.getFileStreamPath(Constants.HINTS_FILE_NAME)
            Log.d("ABS PATH", hintsFile.absolutePath)
            println("ABS PATH " + hintsFile.absolutePath)
            return hintsFile.exists()
        }

        fun getHints(context: Context): List<String> {
            var hintsList: MutableList<String> = mutableListOf()
            val json_string: String =
                context.applicationContext.openFileInput(Constants.HINTS_FILE_NAME).bufferedReader()
                    .use { it.readText() }
            if (json_string != "" && json_string != "\"\"") {
                val parsed = JsonParser().parse(json_string).asJsonArray
                println("#####################HINTS#####################")
                for (hint in parsed) {
                    hintsList.add(hint.asString)
                    println(hint.asString)
                }
            }
            return hintsList
        }

        fun saveHint(context: Context, hint: String) {
            val parsed = Gson().toJson(hint)
            var hintsList: MutableList<String> = getHints(context).toMutableList()
            hintsList.add(parsed)
            saveHints(context, hintsList.toList())
        }

        fun saveHints(context: Context, hintList: List<String>) {
            val parsed = Gson().toJson(hintList)
            context.applicationContext.openFileOutput(Constants.HINTS_FILE_NAME, Context.MODE_PRIVATE)
                .use { it.write(parsed.toByteArray()) }
        }

        fun createFile(context: Context)
        {
            val parsed = Gson().toJson("")
            context.applicationContext.openFileOutput(Constants.HINTS_FILE_NAME, Context.MODE_PRIVATE)
                .use { it.write(parsed.toByteArray()) }
        }
    }
}