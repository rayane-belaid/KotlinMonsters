package org.example.jeu
import org.example.changeCouleur
import org.example.dresseur.Entraineur
import org.example.item.Utilisable
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
                return false // La condition gameOver n'est pas respecté car le joueur n'a pas perdu
            }
        }
        return true
    }

    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv <= 0) { //Vérifie si les monstres sont en vie
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
        if (gameOver() == true){
            return false
        } else{
            println("Souhaitez-vous : 1.Attaquer 2.Utiliser un item 3.Changer de monstre")
            var choix = readln().toInt()
            if (choix == 1){
                monstreJoueur.attaquer(monstreSauvage)
            } else if (choix == 2){
                println(joueur.sacAItems)
                println("Faite votre choix :")
                var indexChoix = readln().toInt()
                var objetChoisi = joueur.sacAItems[indexChoix]
                if (objetChoisi is Utilisable){
                    var captureReussie = objetChoisi.utiliser(monstreSauvage)
                    if (captureReussie == true){
                        return false
                    }
                }
            } else if (choix == 3){
                println("Faite votre choix :")
                for (monstre in joueur.equipeMonstre) {
                    if (monstre.pv > 0) {
                        println(monstre)
                    }
                    else{
                        println(changeCouleur(monstre.toString(),"rouge"))
                    }
                }
                var indexChoix = readln().toInt()
                var choixMonstre: IndividuMonstre= joueur.equipeMonstre[indexChoix]
                if (choixMonstre.pv <= 0) {//Si choixMonstre KO
                    println("Impossible ! Ce monstre KO")
                } else{
                    println("$choixMonstre remplace $monstreJoueur}")
                    monstreJoueur = choixMonstre
                }
                println("$choixMonstre remplace $monstreJoueur")
                monstreJoueur = choixMonstre

                }
            }
    }
//Donne les informations des deux joueurs pendant le combat
    fun afficheCombat(){
        println("================ Début Round : $round =================")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pvMax}")
        println(monstreSauvage.afficheDetail())
        println(monstreJoueur.afficheDetail())
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv / monstreJoueur.pvMax}")
    }

    fun jouer(){
        var joueurPlusRapide = (monstreJoueur.vitesse >= monstreSauvage.vitesse)
        println(afficheCombat())
        if (joueurPlusRapide == true){
            var continuer = actionJoueur()
            if (continuer != false){
                actionAdversaire()
            }
        } else{
            actionAdversaire()
            if (gameOver() == false){
                if (actionJoueur() == false){

                }
            }
        }
    }
    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }
}