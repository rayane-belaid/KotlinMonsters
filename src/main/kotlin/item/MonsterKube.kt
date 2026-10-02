package org.example.item

import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        if (cible.entraineur != null) { //Vérifie si le monstre appartient à un entraîneur
            println("Le monstre ne peut pas être capturé")
        } else { // Si le monstre n'a pas d'entraîneur
            val nbAleatoire = (0..100).random()
            if (nbAleatoire < chanceCapture) { // Comparaison entre la chance de capturer le monstre et la chance de la capturer
                println("Le monstre est capturé !") // Si la chance de le capturer est supérieur au nombre aléatoire
                cible.renommer()
                if (joueur.equipeMonstre.size >= 6) { // Vérifie si l'équipe de monstre contient plus de 6 monstres
                    joueur.boiteMonstre.add(cible) // Le monstre est ajouté à la boîte de monstres
                    println("Le monstre a est ajouté à la boîte de monstre !")
                } else {
                    joueur.equipeMonstre.add(cible)
                    println("Le monstre est ajouté à l'équipe de monstre !") // Si l'équipe de monstre est inférieur à 6 le monstre capturé est ajouté à l'équipe
                }
                cible.entraineur = joueur // Le joueur devient l'entraîneur du monstre
                return true // Indique que la capture a réussie
            } else {
                println("Presque ! Le kube n'a pas pu capturer le monstre !") // Si le nombre aléatoire est supérieur à la chance de capture
            }
        }
        return false
    }
}
