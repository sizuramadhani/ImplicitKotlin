package com.sizura.implicitappkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.sizura.implicitappkotlin.ui.*

class UtamaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container,HomeFragment()).commit()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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
        menuInflater.inflate(R.menu.utama, menu)
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
                supportFragmentManager.beginTransaction().replace(R.id.container,HomeFragment()).commit()
            }
            R.id.nav_camera -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,CameraFragment()).commit()
            }
            R.id.nav_audiomanager -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,AudioManagerFragment()).commit()
            }
            R.id.nav_video -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,VideoFragment()).commit()

            }
            R.id.nav_tts -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,TTSFragment()).commit()

            }
            R.id.nav_audiorecorder -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,AudioRecorderFragment()).commit()

            }
            R.id.nav_email -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,EmailFragment()).commit()

            }
            R.id.nav_phone -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,PhoneFragment()).commit()

            }
            R.id.nav_browser -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,BrowserFragment()).commit()

            }
            R.id.nav_wifi -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,WifiFragment()).commit()

            }
            R.id.nav_whatsapp -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,WhatsuppFragment()).commit()

            }



        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
