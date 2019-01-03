package com.example.wilson.astronautseeker3000

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adefruandta.spinningwheel.SpinningWheelView
import com.example.wilson.astronautseeker3000.model.Planet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val planets = listOf(
        Planet("Duck", 0),
        Planet("Racoon", 0),
        Planet("Panda", 0),
        Planet("Schizo", 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        astronaut_spinning_wheel.items = planets.map { it.name }
        astronaut_spinning_wheel.isEnabled = true

        astronaut_spinning_wheel.onRotationListener =
                object : SpinningWheelView.OnRotationListener<String?> {
                    override fun onRotation() {
                    }

                    override fun onStopRotation(item: String?) {
                        item?.let { winner_text.text = getString(R.string.winner_text) + item }
                    }

                }

        turn_the_wheel_button.setOnClickListener { astronaut_spinning_wheel.rotate(50f, 7000, 50) }
    }
}
