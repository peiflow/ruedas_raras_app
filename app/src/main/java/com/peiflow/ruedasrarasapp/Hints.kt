package com.peiflow.ruedasrarasapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.peiflow.ruedasrarasapp.models.Hint

import kotlinx.android.synthetic.main.activity_hints.*
import kotlinx.android.synthetic.main.content_hints.*

class Hints : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hints)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


    }

    override fun onStart() {
        super.onStart()
        var hintsStr = ""

        val hintsList = Hint.getHints(this)
        for (hint in hintsList){
            hintsStr +=hint +"\r\n"+ "\r\n"
        }
        tv_hints.text = hintsStr
        println(hintsStr)
    }
}
