package com.darkbeast0106.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DbHelper(
    context: Context?
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "tanulo.db"
        const val DB_VERSION = 1
        const val TANULO_TABLE = "tanulo"
        const val COL_ID = "id"
        const val COL_NEV = "nev"
        const val COL_EMAIL = "email"
        const val COL_JEGY = "jegy"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """CREATE TABLE IF NOT EXISTS $TANULO_TABLE (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COL_NEV VARCHAR(255) NOT NULL, 
                $COL_EMAIL VARCHAR(255) NOT NULL,
                $COL_JEGY INTEGER NOT NULL
                )""".trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TANULO_TABLE")
        onCreate(db)
    }

    fun adatRogzites(nev: String?, email: String?, jegy: String?): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NEV, nev)
        values.put(COL_EMAIL, email)
        values.put(COL_JEGY, jegy)
        return db.insert(TANULO_TABLE, null, values) != -1L
    }

    fun adatLekerdezes(): Cursor? {
        val db = this.readableDatabase
        return db.query(
            TANULO_TABLE,
            arrayOf(COL_ID, COL_NEV, COL_EMAIL, COL_JEGY),
            null,
            null,
            null,
            null,
            null
        )
        //return db.rawQuery("SELECT * FROM tanulo WHERE jegy = ?",new String[]{"4"});
    }

    fun idLetezik(id: String): Boolean {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TANULO_TABLE WHERE $COL_ID = ?", arrayOf(id))
        val count = result.count
        result.close()
        return count == 1
    }

    fun adatModosit(id: String, nev: String?, email: String?, jegy: String?): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NEV, nev)
        values.put(COL_EMAIL, email)
        values.put(COL_JEGY, jegy)
        val erintettSorok = db.update(TANULO_TABLE, values, "$COL_ID = ?", arrayOf(id))
        return erintettSorok == 1
    }

    fun adatTorles(id: String): Boolean {
        val db = this.writableDatabase
        return db.delete(TANULO_TABLE, "$COL_ID = ?", arrayOf(id)) == 1
    }
}