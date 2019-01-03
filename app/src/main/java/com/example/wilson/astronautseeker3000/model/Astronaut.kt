package com.example.wilson.astronautseeker3000.model

data class Astronaut(val name: String, var rank: Int) {

    constructor(a: Astronaut) : this(a.name, a.rank)
}