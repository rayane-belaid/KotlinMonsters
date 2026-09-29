package org.example.monstre
import kotlin.math.pow
import org.example.dresseur.Entraineur

class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? =null,
    var expInit: Double
){
    var niveau: Int = 1
    var attaque: Int = this.espece.baseAttaque + (-2..2).random()
    var defense: Int = this.espece.baseDefense + (-2..2).random()
    var vitesse: Int = this.espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = this.espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = this.espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = this.espece.basePv + (-5..5).random()
    var potentiel: Double = 0.5 + Math.random() * 1.5
    var exp: Double = 0.0
        set(value) {
            field = value
            val estNiveau1 = niveau == 1
            while (field >= palierExp()) {
                levelUp()
                if (!estNiveau1) {
                    println("Le monstre $nom est maintenant niveau $niveau !")
                }
            }
        }
    init {
        this.exp = expInit // Applique le setter et déclenche un éventuel level-up
    }
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv.coerceIn(0, pvMax)
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */

    fun palierExp(): Double {
        return 100 * (this.niveau - 1).toDouble().pow(2.0)
    }
    fun levelUp(){
        this.niveau++
        this.attaque = (this.attaque * potentiel).toInt() + (-2..2).random()
        this.defense = (this.defense * potentiel).toInt() + (-2..2).random()
        this.vitesse = (this.vitesse * potentiel).toInt() + (-2..2).random()
        this.attaqueSpe = (this.attaqueSpe * potentiel).toInt() + (-2..2).random()
        this.defenseSpe = (this.defenseSpe * potentiel).toInt() + (-2..2).random()
        this.pv = pvMax + (-5..5).random()
    }
    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */

    fun attaquer(cible : IndividuMonstre){
        var degatBrut = this.attaque
        var degatTotal = degatBrut - (this.defense /2)
        if (degatTotal < 1) {
            degatTotal = 1
        }
        var pvAvant = cible.pv
        cible.pv -= degatTotal
        var pvApres = cible.pv
        println ("${this.nom} inflige ${(pvAvant - pvApres)} dégâts à ${cible.nom}")
        }
    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer(){
        println("Renommer ${this.nom} ?")
        var nouveauNom = readln().toString()
        if (nouveauNom.isEmpty()){
            nom = this.nom
        } else{
            this.nom = nouveauNom
        }
    }
    fun afficheDetail(){
        println("==================")
        println("Nom : $nom")
        println("Exp : $exp")
        println("PV : $pv")
        println("=================")
        println("Attaque : $attaque")
        println("Défense : $defense")
        println("Vitesse : $vitesse")
        println("Attaque Spécial : $attaqueSpe")
        println("Défense spécial : $defenseSpe")
        println("==============================")
        println(espece.afficheArt())
    }
}