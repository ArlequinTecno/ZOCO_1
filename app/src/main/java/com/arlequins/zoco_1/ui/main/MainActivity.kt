package com.arlequins.zoco_1.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.ActivityMainBinding

import com.arlequins.zoco_1.ui.menuDrawer.index.SectionsPagerAdapter

import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        //menu tabs
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.appBarMain.indexViewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.appBarMain.tabs
        tabs.setupWithViewPager(viewPager)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.nav_index){
                tabs.visibility = View.VISIBLE
                viewPager.visibility = View.VISIBLE
            }
            else{
                tabs.visibility = View.GONE
                viewPager.visibility = View.GONE
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_index,
                R.id.nav_category,
                R.id.nav_my_products,
                R.id.nav_notifications,
                R.id.nav_about_zoco,
                R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}