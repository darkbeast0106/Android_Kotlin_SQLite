package com.darkbeast0106.sqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TorolActivity : AppCompatActivity() {
    private lateinit var etId: EditText
    private lateinit var btnTorol: Button
    private lateinit var btnVissza: Button
    private lateinit var adatbazis: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_torol)
        init()
        btnVissza.setOnClickListener {
            val vissza = Intent(this@TorolActivity, MainActivity::class.java)
            startActivity(vissza)
            finish()
        }
        btnTorol.setOnClickListener(View.OnClickListener {
            val id = etId.text.toString().trim()
            if (id.isEmpty()) {
                Toast.makeText(this@TorolActivity, "ID kitöltése kötlező",
                    Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (!adatbazis.idLetezik(id)) {
                Toast.makeText(this@TorolActivity, "Nincs ilyen rekord",
                    Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (adatbazis.adatTorles(id)) {
                Toast.makeText(this@TorolActivity, "Sikeres törlés",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@TorolActivity, "Sikertelen törlés",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        etId = findViewById(R.id.et_id_torol)
        btnTorol = findViewById(R.id.btn_torol)
        btnVissza = findViewById(R.id.btn_torol_vissza)
        adatbazis = DbHelper(this)
    }
}