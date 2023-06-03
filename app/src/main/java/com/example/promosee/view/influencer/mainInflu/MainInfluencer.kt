package com.example.promosee.view.influencer.mainInflu

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.promosee.R
import com.example.promosee.databinding.ActivityMainInfluencerBinding

class MainInfluencer : AppCompatActivity() {

    private lateinit var binding: ActivityMainInfluencerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainInfluencerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navViewInfluencer

        val navController = findNavController(R.id.nav_host_fragment_activity_main_influencer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home_influencer, R.id.navigation_dashboard_influencer, R.id.navigation_notifications_influencer
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}