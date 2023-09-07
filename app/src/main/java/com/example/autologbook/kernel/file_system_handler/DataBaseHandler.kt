package com.example.autologbook.kernel.file_system_handler


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date


class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    fun CreateSingleton(){
        instance = this
    }

    @SuppressLint("SQLiteString")
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                LITRE_COL + " FLOAT," +
                PRICE_COL + " FLOAT," +
                COMMENT_COL + " TEXT," +
                DATE_COL + " FLOAT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun add(
        litre: Float, price: Float,
        comment: String, date: Date ){
        val values = ContentValues()
        values.put(LITRE_COL, litre)
        values.put(PRICE_COL, price)
        values.put(COMMENT_COL, comment)
        values.put(DATE_COL, date.time)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
    }

    fun deleteById(id: Int){
        val db = this.readableDatabase
        db.delete(TABLE_NAME, ID_COL.plus("=?"), arrayOf(id.toString()))
    }

    fun getAll(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }



    companion object{

            @Volatile
            private lateinit var instance: DbHelper

            fun getInstance(): DbHelper {
                    return instance
                }


        private val DATABASE_NAME = "REFILL_LOG"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "REFILL"
        val ID_COL = "id"
        val PRICE_COL = "price"
        val LITRE_COL = "litre"
        val COMMENT_COL = "comment"
        val DATE_COL = "date"
    }
}