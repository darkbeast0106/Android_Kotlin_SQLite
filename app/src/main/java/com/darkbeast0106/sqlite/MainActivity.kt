package com.darkbeast0106.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var btnOlvas: Button
    private lateinit var btnRogzites: Button
    private lateinit var btnTorles: Button
    private lateinit var btnModosit: Button
    private lateinit var textAdatok: TextView
    private lateinit var adatbazis: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listeners()
    }

    private fun adatLekerdezes() {
        val adatok = adatbazis.adatLekerdezes()
        if (adatok == null) {
            Toast.makeText(this, "Hiba történt a lekérdezés során!", Toast.LENGTH_SHORT).show()
            return
        }
        if (adatok.count == 0) {
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show()
            return
        }
        val builder = StringBuilder()
        while (adatok.moveToNext()) {
            builder.append("ID: ${adatok.getInt(0)}\n")
            builder.append("Név: ${adatok.getString(1)}${System.lineSeparator()}")
            builder.append("E-mail: ").append(adatok.getString(2)).append("\n")
            builder.append("Jegy: ").append(adatok.getInt(3)).append("\n\n")
        }
        textAdatok.text = builder
        Toast.makeText(this, "Sikeres adat lekérdezés", Toast.LENGTH_SHORT).show()
    }

    private fun listeners() {
        textAdatok.movementMethod = ScrollingMovementMethod()
        btnOlvas.setOnClickListener { adatLekerdezes() }
        btnRogzites.setOnClickListener {
            val rogzitesre = Intent(this@MainActivity, RogzitActivity::class.java)
            startActivity(rogzitesre)
            finish()
        }
        btnTorles.setOnClickListener {

            val torlesre = Intent(this@MainActivity, TorolActivity::class.java)
            startActivity(torlesre)
            finish()
        }
        btnModosit.setOnClickListener {
            val modositasra = Intent(this@MainActivity, ModositActivity::class.java)
            startActivity(modositasra)
            finish()
        }
    }

    private fun init() {
        btnOlvas = findViewById(R.id.btn_olvas)
        btnRogzites = findViewById(R.id.btn_rogzitesre)
        btnTorles = findViewById(R.id.btn_torlesre)
        btnModosit = findViewById(R.id.btn_modositasra)
        textAdatok = findViewById(R.id.text_adatok)
        adatbazis = DbHelper(this)
    }
}