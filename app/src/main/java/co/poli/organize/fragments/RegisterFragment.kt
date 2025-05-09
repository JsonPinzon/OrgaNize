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

class RegisterFragment : Fragment() {
    private lateinit var tilName: TextInputLayout
    private lateinit var tilRegEmail: TextInputLayout
    private lateinit var tilRegPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etRegEmail: TextInputEditText
    private lateinit var etRegPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setupListeners()
    }

    private fun initializeViews(view: View) {
        tilName = view.findViewById(R.id.tilName)
        tilRegEmail = view.findViewById(R.id.tilRegEmail)
        tilRegPassword = view.findViewById(R.id.tilRegPassword)
        tilConfirmPassword = view.findViewById(R.id.tilConfirmPassword)
        etName = view.findViewById(R.id.etName)
        etRegEmail = view.findViewById(R.id.etRegEmail)
        etRegPassword = view.findViewById(R.id.etRegPassword)
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword)
        btnRegister = view.findViewById(R.id.btnRegister)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            if (validateInputs()) {
                register()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        val name = etName.text.toString().trim()
        if (name.isEmpty()) {
            tilName.error = getString(R.string.error_empty_name)
            isValid = false
        } else {
            tilName.error = null
        }

        val email = etRegEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilRegEmail.error = getString(R.string.error_empty_email)
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilRegEmail.error = getString(R.string.error_invalid_email)
            isValid = false
        } else {
            tilRegEmail.error = null
        }

        val password = etRegPassword.text.toString()
        if (password.isEmpty()) {
            tilRegPassword.error = getString(R.string.error_empty_password)
            isValid = false
        } else if (password.length < 6) {
            tilRegPassword.error = getString(R.string.error_short_password)
            isValid = false
        } else {
            tilRegPassword.error = null
        }

        val confirmPassword = etConfirmPassword.text.toString()
        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.error = getString(R.string.error_empty_confirm_password)
            isValid = false
        } else if (confirmPassword != password) {
            tilConfirmPassword.error = getString(R.string.error_passwords_dont_match)
            isValid = false
        } else {
            tilConfirmPassword.error = null
        }

        return isValid
    }

    private fun register() {
        Toast.makeText(
            requireContext(),
            getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()
        (activity as? LoginActivity)?.navigateToMainActivity()
    }
}