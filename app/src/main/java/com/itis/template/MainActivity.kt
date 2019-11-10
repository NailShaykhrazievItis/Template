package com.itis.template

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_main,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_main.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_main.setNavigationItemSelectedListener(this)
        title = getString(R.string.menu_profile)

        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.nav_host_fragment, Profile(), R.id.nav_friends.toString())
                    .commit()
            nav_view_main.setCheckedItem(R.id.nav_friends)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when (item.itemId) {
            R.id.nav_friends -> Profile()
            R.id.nav_messages -> MessagesFragment()
            R.id.nav_search -> SearchFragment()
            else -> GroupsFragment()
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment, item.itemId.toString())
                .addToBackStack(null)
                .commit()

        drawer_main.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawer_main.isDrawerOpen(GravityCompat.START)) {
            drawer_main.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()

            val itemId = supportFragmentManager.fragments[0].tag?.toInt()
            if (itemId != null) nav_view_main.setCheckedItem(itemId)
        }
    }
}
