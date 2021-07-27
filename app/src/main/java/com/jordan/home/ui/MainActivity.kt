package com.jordan

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jordan.home.ui.HomeFragment
import com.jordan.utils.ManageFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var bottomNavigationView : BottomNavigationView ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        ManageFragment.replaceFrag(this, HomeFragment(), "")


        bottomNavigationView?.setOnNavigationItemSelectedListener { onNavigationItemSelected(it)  }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                ManageFragment.replaceFrag(this, HomeFragment(), "")
            }
            R.id.notification -> {
                Toast.makeText(this, "notification",Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "settings",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}