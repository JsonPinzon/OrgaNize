package co.poli.organize

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.poli.organize.databinding.ActivityMainBinding
import co.poli.organize.fragments.CalendarFragment
import co.poli.organize.fragments.VideoFragment

import co.poli.organize.fragments.settings.SettingsFragment
import co.poli.organize.fragments.tasks.GalleryFragment
import co.poli.organize.fragments.tasks.TasksFragment
import co.poli.organize.fragments.tasks.WebFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbarTitle = binding.toolbarTitle
        toolbarTitle.text = "ORGANIZE"

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.nav_galeria -> loadFragment(GalleryFragment())
                R.id.nav_video -> loadFragment(VideoFragment())
                R.id.nav_web -> loadFragment(WebFragment())
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(TasksFragment())
                    toolbarTitle.text = "ORGANIZE"
                }
                R.id.nav_calendar -> {
                    loadFragment(CalendarFragment())
                    toolbarTitle.text = "CALENDARIO"
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    toolbarTitle.text = "CONFIGURACIÓN"
                }
            }
            true
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_dashboard
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
