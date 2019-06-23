package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peiflow.ruedasrarasapp.models.Hint
import kotlinx.android.synthetic.main.activity_hints.*
import kotlinx.android.synthetic.main.content_hints.*

class TemotoHints : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hints)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        fab_temoto_instructions.setOnClickListener {
            startActivity(Intent(this, TemotoInstructionsPopup::class.java))
//            val inflater:LayoutInflater = applicationContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val customView: View = inflater.inflate(R.layout.temoto_instructions_popup, null)
//            val popupWindow:PopupWindow = PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.WRAP_CONTENT)
//            if(Build.VERSION.SDK_INT>=21){
//                popupWindow.setElevation(5.0f);
//            }
//            ib_close.setOnClickListener {
//                popupWindow.dismiss()
//            }
//            popupWindow.showAtLocation(sv_temoto_instr, Gravity.CENTER,0,0);
       }
    }

    override fun onStart() {
        super.onStart()
        var hintsStr = ""

        val hintsList = Hint.getHints(this)
        for (hint in hintsList) {
            hintsStr += hint + "\r\n" + "\r\n"
        }
        tv_hints.text = hintsStr
        println(hintsStr)
    }
}