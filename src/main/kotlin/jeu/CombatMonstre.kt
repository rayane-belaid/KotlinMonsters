package org.example.jeu
import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre,

) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        //parcourir les monstre de l'equipe du joueur
        for (monstre in joueur.equipeMonstre) {
            //si le monstre de la boucle est vivant
            if (monstre.pv > 0) {
                return false
            }
        }
        return true
    }

    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv <= 0) {
            println("${joueur.nom} a gagné !")
            var gainExp = monstreJoueur.exp * 0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne ${gainExp} exp")
            return true
        } else {
            if (monstreSauvage.entraineur == joueur) {
                println("${monstreSauvage.nom} a été capturé !")
                return true
            } else {
                return false
            }
        }
    }

    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            println("${monstreSauvage.nom} attaque ${monstreJoueur.nom}")
        }
    }

    fun actionJoueur():Boolean{
        println("Souhaitez-vous : 1.Attaquer 2.Utiliser un item 3.Changer de monstre")
    }
}