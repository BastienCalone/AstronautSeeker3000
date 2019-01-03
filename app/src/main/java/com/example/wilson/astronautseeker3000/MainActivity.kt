package com.example.wilson.astronautseeker3000

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.adefruandta.spinningwheel.SpinningWheelView
import com.example.wilson.astronautseeker3000.databinding.ActivityMainBinding
import com.example.wilson.astronautseeker3000.model.Interaction
import com.example.wilson.astronautseeker3000.model.Planet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var planets = listOf(
        Planet("Duck"),
        Planet("Racoon"),
        Planet("Panda"),
        Planet("Schizo", 1)
    )

    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingView: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindingView.viewmodel = viewModel

        astronaut_spinning_wheel.items = planets.map { it.name }

        viewModel.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.winnerName -> updateWinnerMessage(viewModel.winnerName)
                    BR.shouldRotate -> turnTheWheel(viewModel.shouldRotate)
                }
            }
        })

        astronaut_spinning_wheel.onRotationListener =
                object : SpinningWheelView.OnRotationListener<String?> {
                    override fun onRotation() {
                    }

                    override fun onStopRotation(item: String?) {
                        item?.let {
                            viewModel.handleInteraction(Interaction.StopRotate(item))
                        }
                    }
                }

        turn_the_wheel_button.setOnClickListener {
            viewModel.handleInteraction(Interaction.Rotate)
        }
        result.setOnClickListener {
            displayRank()
        }
    }

    private fun updateWinnerMessage(winnerName: String) {
        winner_text.text = getString(R.string.winner_text) + winnerName
        updateWinnerRank(winnerName)
    }

    private fun updateWinnerRank(winner: String) {
        val planet = planets.first { s -> s.name == winner }
        planet.rank++

        if (isBestPlanet(planet)) {
            Toast.makeText(this, "LES SCHIZOS C TELEMEN D MONSTRES", Toast.LENGTH_LONG).show()
        }

        val zero = planets.filter { s -> s.rank < 0 }

    }

    private fun turnTheWheel(shouldRotate: Boolean) {
        if (shouldRotate) {
            astronaut_spinning_wheel.rotate(50f, 7000, 50)
        }
    }

    fun isBestPlanet(planet: Planet) = if (planet.name == "Schizo") true else false

    private fun displayRank() {
        planets = planets.sortedBy { it.rank }
        planets = planets.reversed()
        Toast.makeText(this, "classement : $planets", Toast.LENGTH_LONG).show()
    }
}
