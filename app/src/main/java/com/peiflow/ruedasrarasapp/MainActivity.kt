package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.peiflow.ruedasrarasapp.adapters.EventAdapter
import com.peiflow.ruedasrarasapp.models.EventData
import com.peiflow.ruedasrarasapp.models.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        var eventsList = mutableListOf<EventData>()
        val event = EventData()
        event.uuid = "000"
        event.name = "Ruta sabado"
        event.description = "Ruta del sábado por la mañana a Tiedra"
        event.address =  "Plaza mayor"
        event.locations = mutableListOf(LatLng(41.521052,-5.393818),LatLng(41.650273,-5.269575))
        event.routeUrl = "https://www.google.es/maps/dir/Toro+(Zamora),+Toro/47530+San+Román+de+Hornija,+Valladolid/Castronuño/La+Bóveda+de+Toro/Venialbo/@41.433834,-5.5421648,11z/data=!3m1!4b1!4m32!4m31!1m5!1m1!1s0xd38959c132a704f:0xa2b7a1b491bfb470!2m2!1d-5.3913375!2d41.5274417!1m5!1m1!1s0xd389620bacd852d:0x797ca0d3abb920fc!2m2!1d-5.2847709!2d41.480658!1m5!1m1!1s0xd38bb76f5686111:0x9bf614a77db387a6!2m2!1d-5.2671378!2d41.387238!1m5!1m1!1s0xd38c79765e54131:0xd2d704f21b666fe2!2m2!1d-5.4109725!2d41.3430855!1m5!1m1!1s0xd38c361b0eaff17:0x789cd4ef0164bb81!2m2!1d-5.5367936!2d41.3895764!3e0"
        event.dateTime = "27/07/2019 11:00:00"
        event.imgUrl = "https://image.shutterstock.com/image-vector/colorful-seamless-geometric-pattern-450w-161998277.jpg"
        eventsList.add(event)

        //recycler view
        rv_events.layoutManager = LinearLayoutManager(this)
        rv_events.hasFixedSize()
        rv_events.adapter = EventAdapter(eventsList, {eventItem : EventData -> eventItemClicked(eventItem)})


        /*val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    fun eventItemClicked(eventItem: EventData)
    {
        val intent = Intent(this, EventDetails::class.java)
        intent.putExtra("Event", eventItem)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
