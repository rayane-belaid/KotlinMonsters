package org.example.monde

import org.example.monstre.EspeceMonstre

class Zone (
    var id: Int,
    var nom: String,
    var expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? =null,
    var zonePrecedente: Zone? =null

    //TODO genereMonstre()
    //TODO rencontrerMonstre()
)