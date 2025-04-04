package co.poli.organize

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    // Declaración de variables para los componentes de la UI
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var tvForgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar componentes
        initializeViews()

        // Configurar el ViewPager2 con los fragmentos
        setupViewPager()

        // Configurar los eventos de los componentes
        setupListeners()
    }

    private fun initializeViews() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
    }

    private fun setupViewPager() {
        // Crear el adaptador para el ViewPager2
        val loginPagerAdapter = LoginPagerAdapter(this)
        viewPager.adapter = loginPagerAdapter

        // Conectar el TabLayout con el ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.login)
                1 -> getString(R.string.register)
                else -> null
            }
        }.attach()
    }

    private fun setupListeners() {
        // Configurar el evento para recuperar contraseña
        tvForgotPassword.setOnClickListener {
            // Mostrar diálogo para recuperar contraseña
            showForgotPasswordDialog()
        }
    }

    private fun showForgotPasswordDialog() {
        // Implementación del diálogo para recuperar contraseña
        // Este método mostrará un diálogo donde el usuario puede ingresar
        // su correo para recuperar su contraseña
    }

    // Método para navegar a la MainActivity después de iniciar sesión
    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finalizar esta actividad para que no se pueda volver atrás
    }
}

