package fr.matteo.projetandroid

import java.io.Serializable

data class Halls(
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val parkingInfo: String,
    val description: String,
    val PublicTransport: String
) : Serializable {
}