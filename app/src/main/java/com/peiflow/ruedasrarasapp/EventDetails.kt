package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.models.LatLng

import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_event_details.*
import java.text.SimpleDateFormat
import java.util.*

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
            startActivity(Intent(applicationContext, MainActivity::class.java))
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
                    10f
                )
            )
        } else {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    com.google.android.gms.maps.model.LatLng(
                        markers[0].lat,
                        markers[0].lng
                    ), 15f
                )
            )
        }
        val mapSettings = mMap.uiSettings
        mapSettings.setAllGesturesEnabled(true)
    }

    private fun processEventData(evt: EventData) {
        val spanish = Locale("es", "ES")
        val fmt = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", spanish)
        val date: Date = fmt.parse(evt.dateTime)
        val fmtOut = SimpleDateFormat("EEEE dd/MM/yyyy", spanish)
        val frmtDate: String = fmtOut.format(date)
        val timeOut = SimpleDateFormat("hh:mm:ss", spanish)
        val frmtTime: String = timeOut.format(date)

        desc_text.text = evt.description
        date_text.text = frmtDate.capitalize()
        time_text.text = frmtTime
        address_text.text = evt.address
        markers = evt.locations!!

        this.title = evt.name
    }
}
