package com.example.activitycommunication

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.activitycommunication.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var db = DBHandler(this)
    lateinit var imageUri : String
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageUri = ""

        binding.buttonChooseImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else {
                    pickImageFromGalery()
                }
            }
            else {
                pickImageFromGalery()
            }
        }
    }

    private fun pickImageFromGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    fun ajouter(view: View) {
        var etudiant = Etudiant()

        etudiant.nom = binding.txtNom.text.toString()
        etudiant.prenom = binding.txtPrenom.text.toString()
        etudiant.age = binding.txtAge.text.toString().toInt()
        etudiant.tel = binding.txtTel.text.toString()
        etudiant.image = imageUri

        db.insertEtudiant(etudiant)
    }

    fun afficher(view: View) {
        val intent = Intent(this,SecondaryRecyclerViewActivity::class.java)
        startActivity(intent)

    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode,permissions, grantResults)
            if(grantResults.size> 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                pickImageFromGalery()
            }else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageUri = data?.data.toString()
            binding.buttonChooseImage.text = "Image was chosen"
            Toast.makeText(this, "Image was uploaded sucessfully", Toast.LENGTH_SHORT).show()
        }
    }
}