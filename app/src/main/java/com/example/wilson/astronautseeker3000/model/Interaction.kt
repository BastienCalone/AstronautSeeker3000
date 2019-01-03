package com.example.wilson.astronautseeker3000.model

sealed class Interaction {

    object Rotate : Interaction()
    class StopRotate(val winner: String) : Interaction()
}