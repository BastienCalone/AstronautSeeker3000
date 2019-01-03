package com.example.wilson.astronautseeker3000.model

sealed class PlayerInteraction {

    object Rotate : PlayerInteraction()
    class StopRotate(val winner: String) : PlayerInteraction()

}