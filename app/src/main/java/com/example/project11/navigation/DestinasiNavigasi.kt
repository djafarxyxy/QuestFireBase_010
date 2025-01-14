package com.example.project11.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome : DestinasiNavigasi {
    override val route : String = "home"
    override val titleRes : String = "Home"
}

object DestinasiEntry : DestinasiNavigasi {
    override val route : String = "insert"
    override val titleRes : String = "Insert"
}