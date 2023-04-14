package com.example.myhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myhealth.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onStart() {
        super.onStart()
        val drawer: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.calorieCounterFragment, R.id.homeFragment, R.id.recipeCategoriesFragment, R.id.dietTipsFragment
        ), drawer)

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = findNavController(R.id.myNavHostFragment)
        //navController = navHostFragment.navController
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp()
    }
}