package com.darkbeast0106.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class ModositActivity : AppCompatActivity() {

    private lateinit var etNev: EditText
    private lateinit var etEmail: EditText
    private lateinit var etJegy: EditText
    private lateinit var etId: EditText
    private lateinit var btnModosit: Button
    private lateinit var btnVissza: Button
    private lateinit var adatbazis: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modosit)
        init()
        btnVissza.setOnClickListener {
            val vissza = Intent(this@ModositActivity, MainActivity::class.java)
            startActivity(vissza)
            finish()
        }
        btnModosit.setOnClickListener {
            adatModosit()
        }
    }

    private fun adatModosit() {
        val id = etId.text.toString().trim()
        val nev = etNev.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val jegy = etJegy.text.toString().trim()
        if (id.isEmpty()) {
            Toast.makeText(this, "ID kitöltése kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        if (!adatbazis.idLetezik(id)) {
            Toast.makeText(this, "Ilyen ID-val nem létezik rekord", Toast.LENGTH_SHORT).show()
            return
        }
        if (nev.isEmpty()) {
            Toast.makeText(this, "Név kitöltése kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Email kitöltése kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        if (jegy.isEmpty()) {
            Toast.makeText(this, "Jegy kitöltése kötelező", Toast.LENGTH_SHORT).show()
            return
        }
        val jegySzam = jegy.toInt()
        if (jegySzam < 1 || jegySzam > 5) {
            Toast.makeText(this, "A jegy 1 és 5 közötti szám lehet", Toast.LENGTH_SHORT).show()
            return
        }
        if (adatbazis.adatModosit(id, nev, email, jegy)) {
            Toast.makeText(this, "Sikeres módosítás", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Sikertelen módosítás", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        etNev = findViewById(R.id.et_nev_modosit)
        etEmail = findViewById(R.id.et_email_modosit)
        etJegy = findViewById(R.id.et_jegy_modosit)
        etId = findViewById(R.id.et_id_modosit)
        btnModosit = findViewById(R.id.btn_modosit)
        btnVissza = findViewById(R.id.btn_modosit_vissza)
        adatbazis = DbHelper(this)
    }
}