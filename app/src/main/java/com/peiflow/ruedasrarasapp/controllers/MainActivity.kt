package com.peiflow.ruedasrarasapp.controllers

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.peiflow.ruedasrarasapp.*
import com.peiflow.ruedasrarasapp.adapters.EventAdapter
import com.peiflow.ruedasrarasapp.adapters.FirestoreManager
import com.peiflow.ruedasrarasapp.models.EventData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    var PERMISSION_REQUEST: Int = 200
    val firestoneManager: FirestoreManager = FirestoreManager()
    var eventsList: MutableList<EventData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        checkPermissions()

        firestoneManager.getEventsByDate(this@MainActivity)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        ib_cartel.setOnClickListener {
            startActivity(Intent(this, Cartel::class.java))
        }

        nav_view.setNavigationItemSelectedListener(this)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("asociacionruedasraras@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Ruedas Raras App")
            email.putExtra(Intent.EXTRA_TEXT, android.R.id.message)

            //need this to prompts email client only
            email.type = "message/rfc822"

            startActivity(Intent.createChooser(email, "Choose an Email client :"))
        }
    }

    fun eventItemClicked(eventItem: EventData) {
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
        this.finish()
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
            R.id.nav_qr_reader -> {
                startActivity(Intent(this, QrScanner::class.java))
            }
            R.id.nav_schedule -> {
                val intent = Intent(this, Schedule::class.java)
                val eventsArray: Array<EventData> = eventsList.toTypedArray()
                intent.putExtra("Events", eventsArray)
                startActivity(intent)
            }
            R.id.nav_temoto_hints -> {
                startActivity(Intent(this, TemotoHints::class.java))
            }
            R.id.nav_partners -> {
                startActivity(Intent(this, Partners::class.java))
            }
            R.id.nav_rr_face -> {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://www.facebook.com/Ruedas-Raras-525240697656845/")
                startActivity(openUrl)
            }
            R.id.nav_ayto_face -> {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://www.facebook.com/ToroAyto/")
                startActivity(openUrl)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun checkPermissions() {
        val camPerm: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val readStoragePerm: Int =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        val writeStoragePerm: Int =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        val internetPerm: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        val permissions: Array<String> = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )
        if (camPerm != PackageManager.PERMISSION_GRANTED
            || readStoragePerm != PackageManager.PERMISSION_GRANTED
            || writeStoragePerm != PackageManager.PERMISSION_GRANTED
            || internetPerm != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST)
        }
    }

    fun setupRecyclerView(events: MutableList<EventData>) {
        var recyclerView: RecyclerView = findViewById(R.id.rv_events)
        val linearLayout =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = EventAdapter(events.toList(), this::eventItemClicked)
        recyclerView.layoutManager = linearLayout
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        eventsList = events
    }
}