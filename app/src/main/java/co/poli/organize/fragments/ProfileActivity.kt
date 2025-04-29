package co.poli.organize

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.poli.organize.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = "PERFIL"

        binding.textName.text = "Juan Perez"
        binding.textEmail.text = "juan.perez@example.com"
        binding.textBio.text = "Lifelong learner and Android enthusiast."
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
