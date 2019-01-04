package io.wallet9

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import io.wallet9.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the activity view
        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        drawerLayout = binding.drawerLayout
        navView = binding.navView

        // toolbar
        setSupportActionBar(binding.toolbar)

        val navHost: NavHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment ?: return

        // action bar
        val navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBar(navController, appBarConfiguration)
        setupNavMenu(navController)
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@MainActivity, "Navigated to $dest", Toast.LENGTH_LONG).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val _menu = super.onCreateOptionsMenu(menu)
        if (navView == null) {
            menuInflater.inflate(R.menu.overflow_menu, menu)
            return true
        }
        return _menu
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun setupBottomNavMenu(navController: NavController) {

    }

    private fun setupNavMenu(navController: NavController) {
    }

    private fun setupActionBar(navController: NavController,
                               appBarConfig: AppBarConfiguration) {
    }
}
