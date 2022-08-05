package com.example.goodplanet

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goodplanet.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle = intent.extras
        val email =bundle?.getString("email")
        val provider =bundle?.getString("provider")
        setup( email ?:"", provider ?:"")

        //GUARADADO DE DATOS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()

    }

    private fun setup (email: String, provider: String) {
       title = "Inicio"


        binding.editemail.text= email
        binding.editProveedor.text= provider

        binding.logOutButton.setOnClickListener{
            //Borrado de datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

}