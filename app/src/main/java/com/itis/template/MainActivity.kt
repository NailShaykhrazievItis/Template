package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller =
            (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController

        controller.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = when (destination.id) {
                R.id.homeFragment -> getString(R.string.home)
                R.id.chatFragment -> getString(R.string.chat)
                R.id.profileFragment -> getString(R.string.profile)
                R.id.notificationFragment -> getString(R.string.notifications)
                R.id.settingsFragment -> getString(R.string.settings)
                else -> getString(R.string.app_name)
            }
        }

        bnv_main.setupWithNavController(controller)

    }

    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()
}
