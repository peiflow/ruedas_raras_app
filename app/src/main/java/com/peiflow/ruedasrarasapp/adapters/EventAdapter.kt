package com.peiflow.ruedasrarasapp.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peiflow.ruedasrarasapp.R
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.utils.ImageUtils
import kotlinx.android.synthetic.main.event_list_item.view.*

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
        fun bind(event: EventData, clickListener: (EventData) -> Unit) {
            itemView.tv_event_item_name.text = event.name
            itemView.tv_event_item_datetime.text = event.dateTime
            ImageUtils(itemView.card_image_btn).execute(event.imgUrl)
            itemView.card_image_btn.setOnClickListener { clickListener(event)  }
            itemView.setOnClickListener { clickListener(event) }
        }
    }
}
