package co.poli.organize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.poli.organize.fragments.CalendarFragment
import co.poli.organize.fragments.CreateTaskFragment
import co.poli.organize.fragments.DashboardFragment
import co.poli.organize.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los componentes de la UI
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar componentes
        initializeViews()

        // Configurar la Toolbar
        setSupportActionBar(toolbar)

        // Configurar los eventos de los componentes
        setupListeners()

        // Mostrar el fragmento inicial (Dashboard)
        /*if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
        }*/
    }

    private fun initializeViews() {
        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun setupListeners() {
        // Configurar el navegador inferior
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.nav_calendar -> {
                    loadFragment(CalendarFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

        // Configurar el botón flotante para añadir tareas
        fabAddTask.setOnClickListener {
            showCreateTaskFragment()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun showCreateTaskFragment() {
        // Mostrar el fragmento para crear una nueva tarea
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreateTaskFragment())
            .addToBackStack(null) // Permite volver al fragmento anterior
            .commit()
    }
}