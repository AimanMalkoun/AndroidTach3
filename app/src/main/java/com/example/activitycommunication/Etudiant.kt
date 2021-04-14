package com.example.activitycommunication

class Etudiant{

    var nom : String = ""
    var prenom : String = ""
    var age : Int = 0
    var tel : String = ""
    var image : String = ""


    constructor(nom: String, prenom: String, age: Int, tel: String, image : String) {
        this.nom = nom
        this.prenom = prenom
        this.age = age
        this.tel = tel
        this.image = image
    }

    constructor() {
    }



}