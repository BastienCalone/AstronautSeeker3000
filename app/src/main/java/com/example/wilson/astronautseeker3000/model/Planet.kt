package com.example.wilson.astronautseeker3000.model

data class Planet(val name: String, var rank: Int) {

    constructor(name: String) : this(name, 0)
}