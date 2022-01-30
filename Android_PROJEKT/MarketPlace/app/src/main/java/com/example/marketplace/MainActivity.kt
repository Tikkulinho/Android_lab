package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var timelineViewModel: Timeline_ViewModel
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            true
        }

        R.id.action_search -> {
            val factory = Timeline_ViewModelFactory(Repository())
            timelineViewModel = ViewModelProvider(this, factory).get(Timeline_ViewModel::class.java)

            val auxList = timelineViewModel.products.value
            Log.d("xxx", "auxList: ${auxList}")
            val searchView: SearchView = item.actionView as SearchView
            searchView.queryHint = "Search product"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        timelineViewModel.products.value = auxList?.filter {
                            it.title.contains(
                                newText
                            )
                        } as MutableList<Model_Timeline>?
                    } else {
                        timelineViewModel.products.value = auxList
                    }
                    return true
                }
            })
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}