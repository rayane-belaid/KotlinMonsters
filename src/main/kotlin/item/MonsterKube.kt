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
        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé")
        } else {
            val nbAleatoire = (0..100).random()
            if (nbAleatoire < chanceCapture) {
                println("Le monstre est capturé !")
                cible.renommer()
                if (joueur.equipeMonstre.size >= 6) {
                    joueur.boiteMonstre.add(cible)
                    println("Le monstre a est ajouté à la boîte de monstre !")
                } else {
                    joueur.equipeMonstre.add(cible)
                    println("Le monstre est ajouté à l'équipe de monstre !")
                }
                cible.entraineur = joueur
                return true
            } else {
                println("Presque ! Le kube n'a pas pu capturer le monstre !")
            }
        }
        return false
    }
}
