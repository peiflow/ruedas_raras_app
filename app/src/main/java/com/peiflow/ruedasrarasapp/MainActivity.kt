package com.peiflow.ruedasrarasapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.peiflow.ruedasrarasapp.adapters.DatabaseManager
import com.peiflow.ruedasrarasapp.adapters.EventAdapter
import com.peiflow.ruedasrarasapp.models.EventData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.R.id.message



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var REQUEST_CODE: Int = 100
    var PERMISSION_REQUEST: Int = 200

    var dbm: DatabaseManager = DatabaseManager()
    var eventsList: MutableList<EventData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        checkPermissions()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        ib_cartel.setOnClickListener{
            startActivity(Intent(this, Cartel::class.java))
        }


        nav_view.setNavigationItemSelectedListener(this)
        dbm.ReadDatabase(eventsList)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("asociacionruedasraras@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Ruedas Raras App")
            email.putExtra(Intent.EXTRA_TEXT, message)

            //need this to prompts email client only
            email.type = "message/rfc822"

            startActivity(Intent.createChooser(email, "Choose an Email client :"))
        }
    }

    override fun onStart() {
        setupEventsList()
        super.onStart()
    }

    override fun onResume() {
        setupEventsList()
        super.onResume()
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
                val eventsArray:Array<EventData> = eventsList.toTypedArray()
                intent.putExtra("Events", eventsArray)
                startActivity(intent)
            }
            R.id.nav_temoto -> {
                startActivity(Intent(this, Hints::class.java))
            }
            R.id.nav_tools -> {

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
        val camPerm: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val gpsPerm: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val readStoragePerm: Int =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val writeStoragePerm: Int =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val internetPerm: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)

        val permissions: Array<String> = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.INTERNET
        )


        if (camPerm != PackageManager.PERMISSION_GRANTED
            || gpsPerm != PackageManager.PERMISSION_GRANTED
            || readStoragePerm != PackageManager.PERMISSION_GRANTED
            || writeStoragePerm != PackageManager.PERMISSION_GRANTED
            || internetPerm != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST)
        }
    }

    private fun setupEventsList() {
        rv_events.layoutManager = LinearLayoutManager(this)
        rv_events.hasFixedSize()
        rv_events.adapter = EventAdapter(eventsList, { eventItem: EventData -> eventItemClicked(eventItem) })
    }
}
