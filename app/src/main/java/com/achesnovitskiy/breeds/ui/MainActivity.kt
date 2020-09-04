package com.achesnovitskiy.breeds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.achesnovitskiy.breeds.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController(R.id.main_nav_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWithNavController(main_bottom_navigation_view, navController)

        main_bottom_navigation_view.setOnNavigationItemReselectedListener {
            navController.navigate(it.itemId)
        }
    }
}