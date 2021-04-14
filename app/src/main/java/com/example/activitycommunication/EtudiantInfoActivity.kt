package com.example.activitycommunication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.activitycommunication.databinding.ActivityEtudiantInfoBinding

class EtudiantInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityEtudiantInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEtudiantInfoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_etudiant_info)


        binding.textViewNom.text = intent.getStringExtra("nom")
        binding.textViewPrenom.text = intent.getStringExtra("prenom")
        binding.textViewAge.text = intent.getIntExtra("age",-1).toString()
        binding.textViewTel.text = intent.getStringExtra("tel")
        binding.imageView.setImageURI(intent.getStringExtra("image")?.toUri())
    }
}