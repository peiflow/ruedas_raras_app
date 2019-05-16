package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.peiflow.ruedasrarasapp.adapters.EventAdapter
import com.peiflow.ruedasrarasapp.models.EventData
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_schedule.*
import java.util.*

class Schedule : AppCompatActivity() {
    private lateinit var eventsList:List<EventData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        eventsList = mutableListOf()
        val evntArray: Array<EventData> = intent.extras.getSerializable("Events") as Array<EventData>

        if(evntArray != null)
        {
            eventsList = Arrays.asList(*evntArray)
        }


        println("EVENTS: $evntArray.size")

        tabBtn1.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn2.isChecked = false
                tabBtn3.isChecked = false
                tabBtn1.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor))
            }
        }
        tabBtn2.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn1.isChecked = false
                tabBtn3.isChecked = false
                tabBtn2.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor))
                schedule_rv_events.layoutManager = LinearLayoutManager(this)
                schedule_rv_events.hasFixedSize()
                schedule_rv_events.adapter = EventAdapter(eventsList, { eventItem: EventData -> eventItemClicked(eventItem) })
            }
        }
        tabBtn3.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn1.isChecked = false
                tabBtn2.isChecked = false
                tabBtn3.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor))
            }
        }

    }

    private fun resetBtns()
    {
        tabBtn1.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryDarkColor))
        tabBtn2.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryDarkColor))
        tabBtn3.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryDarkColor))
    }

    override fun onBackPressed() {
        this.finish()
    }

    fun eventItemClicked(eventItem: EventData) {
        val intent = Intent(this, EventDetails::class.java)
        intent.putExtra("Event", eventItem)
        startActivity(intent)
    }

}
