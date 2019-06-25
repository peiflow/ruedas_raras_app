package com.peiflow.ruedasrarasapp.controllers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.peiflow.ruedasrarasapp.R
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.models.LatLng
import com.peiflow.ruedasrarasapp.utils.DateTimeUtils
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_event_details.*

class EventDetails : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var markers: MutableList<LatLng>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    MainActivity::class.java
                )
            )
            this.finish()
        }

        val evt = intent.extras.getSerializable("Event") as EventData
        processEventData(evt)

        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment1) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val opnMapsBtn: Button = findViewById(R.id.open_maps_button)


        opnMapsBtn.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(evt.routeUrl)
            startActivity(openUrl)
        }
//        mapFragment.view?.setOnFocusChangeListener { v, hasFocus ->
//            Log.d("FOCUS", "$v FOCUS GAINED? $hasFocus")
//        }
    }

    override fun onBackPressed() {
        this.finish()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        var lats = 0.0
        var lons = 0.0

        mMap = googleMap
        for (i in 0 until markers.size) {
            var title: String = ""
            if (i == 0) {
                title = "Inicio"

            } else if (i == markers.size - 1) {
                title = "Fin"
            }
            mMap.addMarker(
                MarkerOptions().position(
                    com.google.android.gms.maps.model.LatLng(
                        markers[i].lat,
                        markers[i].lng
                    )
                ).title(title)
            )

            lats += markers[i].lat
            lons += markers[i].lng

        }
        if (markers.size > 1) {
            val lat: Double = lats / markers.size
            val lon: Double = lons / markers.size
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    com.google.android.gms.maps.model.LatLng(lat, lon),
                    17f
                )
            )
        } else {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    com.google.android.gms.maps.model.LatLng(
                        markers[0].lat,
                        markers[0].lng
                    ), 17f
                )
            )
        }
        val mapSettings = mMap.uiSettings
        mapSettings.setAllGesturesEnabled(true)
    }

    private fun processEventData(evt: EventData) {
        val frmtDate = DateTimeUtils.getSpanishLongDate(evt.dateTime!!)
        val frmtTime = DateTimeUtils.getShortTime(evt.dateTime!!)
        desc_text.text = evt.description
        date_text.text = frmtDate.capitalize()
        time_text.text = frmtTime
        address_text.text = evt.address
        markers = evt.locations!!
        this.title = evt.name
    }
}