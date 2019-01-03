package com.example.wilson.astronautseeker3000

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wilson.astronautseeker3000.model.Astronaut

class OtherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
    }

    fun synthax() {
        val wilson = Astronaut("george", 1)
        val b = wilson.copy()
        val c = Astronaut(b)
    }
}
