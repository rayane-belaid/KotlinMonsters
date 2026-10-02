package org.example.monde

import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

class Zone (
    var id: Int,
    var nom: String,
    var expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? =null,
    var zonePrecedente: Zone? =null
) {
    fun genereMonstre(): IndividuMonstre {
        var espece_hasard = especesMonstres.random()
        espece_hasard.
        }
    }
}

    //TODO rencontrerMonstre()