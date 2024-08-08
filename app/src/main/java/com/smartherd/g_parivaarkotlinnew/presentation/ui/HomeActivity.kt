package com.smartherd.g_parivaarkotlinnew.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.smartherd.g_parivaarkotlinnew.R
import com.smartherd.g_parivaarkotlinnew.databinding.ActivityHomeBinding
import com.smartherd.g_parivaarkotlinnew.presentation.ui.fragment.HomeFragment
import com.smartherd.g_parivaarkotlinnew.presentation.ui.fragment.OrderFragment

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val fragment_container = findViewById<FrameLayout>(R.id.flContainerFragment)
        drawerLayout = findViewById(R.id.drawerLayout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainerFragment, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_dashboard)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> supportFragmentManager.beginTransaction()
                .replace(R.id.flContainerFragment, HomeFragment()).commit()
            R.id.nav_order -> supportFragmentManager.beginTransaction()
                .replace(R.id.flContainerFragment, OrderFragment()).commit()

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment){

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContainerFragment, fragment)
        transaction.commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}