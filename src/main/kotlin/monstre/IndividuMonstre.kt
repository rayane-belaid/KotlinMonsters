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

            while (field >= palierExp()) {
                levelUp()
            }
        }
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv.coerceIn(0, pvMax)
        }


    fun palierExp(): Double {
        return 100 * (this.niveau - 1).toDouble().pow(2.0)
    }
    fun levelUp(){
        this.niveau++
        this.attaque * potentiel + (-2..2).random()
        this.defense * potentiel + (-2..2).random()
        this.vitesse * potentiel + (-2..2).random()
        this.attaqueSpe * potentiel + (-2..2).random()
        this.defenseSpe * potentiel + (-2..2).random()
        this.pv = pvMax + (-5..5).random()
    }
}