package co.poli.organize.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.poli.organize.LoginActivity
import co.poli.organize.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    // Declaración de variables para los componentes de la UI
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar componentes
        initializeViews(view)

        // Configurar eventos
        setupListeners()
    }

    private fun initializeViews(view: View) {
        tilEmail = view.findViewById(R.id.tilEmail)
        tilPassword = view.findViewById(R.id.tilPassword)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        btnLogin = view.findViewById(R.id.btnLogin)
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            if (validateInputs()) {
                // Realizar el proceso de inicio de sesión
                login()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        // Validar correo electrónico
        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = getString(R.string.error_empty_email)
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = getString(R.string.error_invalid_email)
            isValid = false
        } else {
            tilEmail.error = null
        }

        // Validar contraseña
        val password = etPassword.text.toString()
        if (password.isEmpty()) {
            tilPassword.error = getString(R.string.error_empty_password)
            isValid = false
        } else {
            tilPassword.error = null
        }

        return isValid
    }

    private fun login() {
        // En una implementación real, aquí conectaríamos con el backend
        // Por ahora, simularemos un inicio de sesión exitoso

        // Mostrar un mensaje de éxito
        Toast.makeText(
            requireContext(),
            getString(R.string.login_success),
            Toast.LENGTH_SHORT
        ).show()

        // Navegar a la pantalla principal
        (activity as? LoginActivity)?.navigateToMainActivity()
    }
}