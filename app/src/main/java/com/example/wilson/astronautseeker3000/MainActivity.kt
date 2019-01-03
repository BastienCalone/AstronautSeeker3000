package com.example.wilson.astronautseeker3000

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adefruandta.spinningwheel.SpinningWheelView.OnRotationListener
import com.example.wilson.astronautseeker3000.databinding.ActivityMainBinding
import com.example.wilson.astronautseeker3000.model.PlayerInteraction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewmodel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewmodel
        viewmodel.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.winnerName -> updateWinnerMessage(viewmodel.winnerName)
                    BR.triggerRotation -> turnTheWheel(viewmodel.triggerRotation)
                }
            }
        })

        astronaut_spinning_wheel.items = listOf("Duck", "Racoon", "Panda", "Schizo")
        astronaut_spinning_wheel.onRotationListener = object : OnRotationListener<String> {

            override fun onStopRotation(item: String) {
                viewmodel.handlePlayerInteraction(PlayerInteraction.StopRotate(item))
            }

            override fun onRotation() {
            }

        }

        rotate_wheel.setOnClickListener {
            viewmodel.handlePlayerInteraction(PlayerInteraction.Rotate)
        }

        next.setOnClickListener {
            startActivity(Intent(this, OtherActivity::class.java))
        }
    }

    private fun updateWinnerMessage(winner: String) {
        winner_text.text = getString(R.string.winner_text) + " $winner breh breh breh"
    }

    private fun turnTheWheel(shouldTurn: Boolean) {
        if (shouldTurn)
            astronaut_spinning_wheel.rotate(50F, 7000, 50)
    }
}
