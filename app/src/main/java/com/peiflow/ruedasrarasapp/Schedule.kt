package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import com.peiflow.ruedasrarasapp.adapters.EventAdapter
import com.peiflow.ruedasrarasapp.models.EventData
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_schedule.*
import java.text.SimpleDateFormat
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
            eventsList = Arrays.asList(*evntArray)

        schedule_rv_events.layoutManager = LinearLayoutManager(this)
        schedule_rv_events.hasFixedSize()

    }

    override fun onStart(){
        super.onStart()
        setupOnClickListeners()
        tabBtn1.isChecked = true
    }

    override fun onBackPressed() {
        this.finish()
    }

    private fun resetBtns()    {
        tabBtn1.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
        tabBtn2.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
        tabBtn3.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
    }

    private fun eventItemClicked(eventItem: EventData) {
        val intent = Intent(this, EventDetails::class.java)
        intent.putExtra("Event", eventItem)
        startActivity(intent)
    }

    private fun filterEvents(i:Int) : List<EventData> {
        var resultList: MutableList<EventData> = mutableListOf()
        var date: Date
        val fmt = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        when(i){
            0->{
                for (event in eventsList){
                    date = fmt.parse(event.dateTime)
                    if(date.date == 26){
                        resultList.add(event)
                    }
                }
            }
            1->{
                for (event in eventsList){
                    date = fmt.parse(event.dateTime)
                    if(date.date == 27){
                        resultList.add(event)
                    }
                }
            }
            2->{
                for (event in eventsList){
                    date = fmt.parse(event.dateTime)
                    if(date.date == 28){
                        resultList.add(event)
                    }
                }
            }
        }
        return resultList
    }

    private fun setupOnClickListeners(){
        tabBtn1.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn2.isChecked = false
                tabBtn3.isChecked = false
                tabBtn1.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryColor))
                schedule_rv_events.adapter = EventAdapter(filterEvents(0), { eventItem: EventData -> eventItemClicked(eventItem) })
            }
        }
        tabBtn2.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn1.isChecked = false
                tabBtn3.isChecked = false
                tabBtn2.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryColor))
                schedule_rv_events.adapter = EventAdapter(filterEvents(1), { eventItem: EventData -> eventItemClicked(eventItem) })
            }
        }
        tabBtn3.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                resetBtns()
                tabBtn1.isChecked = false
                tabBtn2.isChecked = false
                tabBtn3.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryColor))
                schedule_rv_events.adapter = EventAdapter(filterEvents(2), { eventItem: EventData -> eventItemClicked(eventItem) })
            }
        }
    }
}
