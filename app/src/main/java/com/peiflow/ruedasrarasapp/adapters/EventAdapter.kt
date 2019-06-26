package com.peiflow.ruedasrarasapp.adapters

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.common.io.Resources
import com.peiflow.ruedasrarasapp.R
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.utils.DateTimeUtils
import com.peiflow.ruedasrarasapp.utils.ImageUtils
import kotlinx.android.synthetic.main.event_list_item.view.*
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EventAdapter (val eventsList: List<EventData>, val clickListener: (EventData) -> Unit): Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as EventViewHolder).bind(eventsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind( event: EventData, clickListener: (EventData) -> Unit) {
            itemView.event_list_item_linear_layout.setOnClickListener{clickListener(event)}
            itemView.tv_event_item_name.text = event.name
            itemView.tv_event_item_datetime.text = DateTimeUtils.convertDateTimeFormat(event.dateTime!!)
            itemView.setOnClickListener { clickListener(event) }
        }
    }
}
