package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNav?.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.menu_timeline -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.timeline_fragment)
                }
                R.id.menu_mymarket -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.mymarket_fragment)
                }
                R.id.menu_myfare -> {
                    //findNavController(R.id.nav_host_fragment).navigate(R.id.myFareFragment)
                }

            }
            menuItem.isChecked=true
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.register_fragment || destination.id == R.id.forgotpassword_fragment
                || destination.id == R.id.login_fragment) {

                bottomNav.visibility = View.GONE
            } else {

                bottomNav.visibility = View.VISIBLE
            }
        }

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

}