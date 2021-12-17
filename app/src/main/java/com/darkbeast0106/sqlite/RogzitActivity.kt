package com.darkbeast0106.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class RogzitActivity : AppCompatActivity() {
    private lateinit var btnRogzit: Button
    private lateinit var btnVissza: Button
    private lateinit var etNev: EditText
    private lateinit var etEmail: EditText
    private lateinit var etJegy: EditText
    private lateinit var adatbazis: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rogzit)
        init()
        btnRogzit.setOnClickListener { adatRogzites() }
        btnVissza.setOnClickListener {
            val vissza = Intent(this@RogzitActivity, MainActivity::class.java)
            startActivity(vissza)
            finish()
        }
    }

    private fun adatRogzites() {
        val nev = etNev.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val jegy = etJegy.text.toString().trim()
        if (nev.isEmpty()) {
            Toast.makeText(this, "Név megadása kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "E-mail megadása kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        if (jegy.isEmpty()) {
            Toast.makeText(this, "Jegy megadása kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        val jegySzam = jegy.toInt()
        if (jegySzam < 1 || jegySzam > 5) {
            Toast.makeText(this, "Jegy 1 és 5 közötti szám lehet", Toast.LENGTH_SHORT).show()
            return
        }
        if (adatbazis.adatRogzites(nev, email, jegy)) {
            Toast.makeText(this, "Sikeres rögzítés", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Sikertelen rögzítés", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        btnRogzit = findViewById(R.id.btn_rogzit)
        btnVissza = findViewById(R.id.btn_rogzit_vissza)
        etNev = findViewById(R.id.et_nev)
        etEmail = findViewById(R.id.et_email)
        etJegy = findViewById(R.id.et_jegy)
        adatbazis = DbHelper(this)
    }
}