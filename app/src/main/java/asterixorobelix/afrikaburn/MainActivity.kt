package asterixorobelix.afrikaburn

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import asterixorobelix.afrikaburn.databinding.ActivityMainBinding
import asterixorobelix.utilities.base.BaseActivity
import asterixorobelix.utilities.interfaces.BottomNavViewTogglable
import asterixorobelix.utilities.ui.setAnimatedVisibility
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavViewTogglable {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override val layout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_map,
                R.id.nav_projects,
                R.id.nav_about,
                R.id.locationFragment,
                R.id.eventFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding?.apply {
            NavigationUI.setupWithNavController(
                bottomNavView,
                navController
            )
        }

        //todo crashlytics and analytics
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun toggleLoadingIndicator(visible: Boolean) {
        binding?.shimmerLayout?.setAnimatedVisibility(visible)
    }

    override fun toggleBottomNavBar(visible: Boolean) {
        binding?.bottomNavView?.let {
            it.setAnimatedVisibility(visible)
        }
    }

    //todo memory leaks
    //todo linkedinProfile
    //todo share this app
}