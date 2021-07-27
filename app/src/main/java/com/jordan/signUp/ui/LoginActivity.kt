package com.jordan

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jordan.home.ui.HomeFragment
import com.jordan.signUp.ui.SignInFragment
import com.jordan.utils.ManageFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ManageFragment.replaceFrag(this, SignInFragment(), "")
    }
}