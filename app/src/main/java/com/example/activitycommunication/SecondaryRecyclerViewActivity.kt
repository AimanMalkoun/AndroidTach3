package com.example.activitycommunication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activitycommunication.databinding.ActivitySecondaryRecyclerViewBinding

class SecondaryRecyclerViewActivity : AppCompatActivity(), EtudiantRecyclerAdapter.OnItemClickListener {

    lateinit var etudiants : List<Etudiant>
    lateinit var binding: ActivitySecondaryRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bd = DBHandler(this)

        etudiants = bd.readEtudiants()
        binding.recyclerView.adapter = EtudiantRecyclerAdapter(etudiants, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)



    }

    override fun onItemClick(position: Int) {
        val etudiant:Etudiant = etudiants.get(position)
        val intent = Intent(this, EtudiantInfoActivity::class.java)
        intent.putExtra("nom", etudiant.nom)
        intent.putExtra("prenom", etudiant.prenom)
        intent.putExtra("age", etudiant.age)
        intent.putExtra("tel", etudiant.tel)
        intent.putExtra("image", etudiant.image)
        startActivity(intent)
    }
}