package com.example.activitycommunication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val db_name = "MyDB"
val table_name = "etdiants"
val col_id = "id"
val col_nom = "nom"
val col_prenom = "prenom"
val col_age = "age"
val col_tel = "tel"
val col_image = "image"

class DBHandler(var context : Context) :  SQLiteOpenHelper(context, db_name,null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + table_name + " (" +
                col_id +  " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                col_nom + " VARCHAR(255)," +
                col_prenom + " VARCHAR(255)," +
                col_age + " INTEGER," +
                col_tel + " VARCHAR(255),"+
                col_image + " VARCHAR(255)"+
                ")"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertEtudiant(etudiant: Etudiant){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(col_nom, etudiant.nom)
        cv.put(col_prenom, etudiant.prenom)
        cv.put(col_age, etudiant.age)
        cv.put(col_tel, etudiant.tel)
        cv.put(col_image, etudiant.image)

        var result = db.insert(table_name, null, cv)
        if(result == (-1).toLong()){
            Toast.makeText(context, "Data Could not be inserted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Data was inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun readEtudiants() :List<Etudiant>{
        var etudiants = ArrayList<Etudiant>()

        val db = this.readableDatabase
        var query = "SELECT * FROM " + table_name
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){

            do{
                var etudiant = Etudiant()
                etudiant.nom = result.getString(result.getColumnIndex(col_nom)).toString()
                etudiant.prenom = result.getString(result.getColumnIndex(col_prenom)).toString()
                etudiant.age = result.getInt(result.getColumnIndex(col_age)).toInt()
                etudiant.tel = result.getString(result.getColumnIndex(col_tel)).toString()
                etudiant.image = result.getString(result.getColumnIndex(col_image)).toString()
                etudiants.add(etudiant)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return etudiants
    }

}