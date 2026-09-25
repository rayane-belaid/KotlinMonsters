package org.example.monstre

import java.io.File


/**
 * Représente une espèce de monstre avec ses caractéristiques de base,
 * ses statistiques et ses différents modificateurs.
 *
 * @property id Identifiant unique de l'espèce du monstre.
 * @property nom Nom de l'espèce du monstre.
 * @property type catégorie du monstre auquel il appartient.
 *
 * @property baseAttaque valeur des attaques de base du monstre.
 * @property baseDefense valeur de défense de base du monstre.
 * @property baseVitesse valeur de vitesse de base du monstre.
 * @property baseAttaqueSpe valeur d'attaque spéciale de base du monstre.
 * @property baseDefenseSpe valeur de défense spéciale de base du monstre.
 * @property basePv nombre de points de vie initial que possède le monstre.
 *
 * @property modAttaque modificateur appliqué à l'attaque.
 * @property modDefense modificateur appliqué à la défense.
 * @property modVitesse modificateur appliqué à la vitesse.
 * @property modAttaqueSpe modificateur appliqué à l'attaque spéciale.
 * @property modDefenseSpe modificateur appliqué à la défense spéciale.
 * @property modPv modificateur appliqué aux points de vie.
 *
 * @property description description générale de l'espèce du monstre.
 * @property particularites particularités propres à l'espèce du monstre.
 * @property caractères caractéristiques ou traits particuliers de l'espèce.
 */


class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
    ) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace détermine si l'art affiché est de face (true) ou de dos (false).
     * La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     * L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */

    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art= File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }

}
