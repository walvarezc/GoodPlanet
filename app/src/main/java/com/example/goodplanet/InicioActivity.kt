package com.example.goodplanet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goodplanet.databinding.ActivityInicioBinding
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.google.firebase.auth.FirebaseAuth


class InicioActivity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null
    private var menuBotones: FloatingActionsMenu? = null
    private var boton1: FloatingActionButton? = null
    private lateinit var binding: ActivityInicioBinding

//    val fab2 :FloatingActionButton = findViewById(R.id.fab2)
//    val fab3 :FloatingActionButton = findViewById(R.id.fab3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)

//        edtUsername = findViewById(R.id.edtUsername)
//        edtPassword = findViewById(R.id.edtPassword)
//        menuBotones = findViewById(R.id.fab)
////        boton1 = findViewById(R.id.fab1)

        val btnBackPassword = findViewById<Button>(R.id.btnViewBackPassword)
        btnBackPassword.setOnClickListener {
            val intento = Intent(this, RecoveryPassActivity::class.java)
            startActivity(intento)
        }

        val Registrar = findViewById<Button>(R.id.btn_irRegister)
        Registrar.setOnClickListener {
            val intento = Intent(this, RegisterActivity::class.java)
            startActivity(intento)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_inicio_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.salir -> {
            finish()
            true
        }
        R.id.action_search -> {
            Toast.makeText(this, R.string.txt_action_search, Toast.LENGTH_LONG).show()
            true
        }

        R.id.action_phone -> {
            Toast.makeText(this, R.string.txt_action_phone, Toast.LENGTH_LONG).show()
            true

        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    fun onlogin(botonLogin: View) {
        val username = edtUsername!!.text.toString()
        val password = edtPassword!!.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(username)
                    } else {
                        getToast("error al autenticar")
                    }
                }

        } else {
            getToast("error al loguear")
        }

    }

    private fun showHome(username: String) {

        val intento = Intent(this, MenuActivity::class.java).apply {
            putExtra("email", username)
        }

        startActivity(intento)

        getToast("wellcome")
    }

    private fun getToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

//    fun onRegister(view: android.view.View) {
//        val intento = Intent(this,RegisterActivity::class.java)
//        startActivity(intento)
//
//        }
    //====================================================================================

    fun facebookIntent(view: android.view.View) {
        val url = "https:/www.facebook.com/search/top?q=good%20planet/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        menuBotones?.collapse()
    }


    fun InstagramIntent(view: android.view.View) {
        val url = "https://instagram.com/goodplanet_app?utm_medium=copy_link/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        menuBotones?.collapse()
    }

    fun WhatsAppIntent(view: android.view.View) {
        val url = "https://wa.me/57 316 8105790/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        menuBotones?.collapse()
    }

    fun onPhone(item: android.view.MenuItem) {
        val intento = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "3168105790"))
        startActivity(intento)
    }


}







