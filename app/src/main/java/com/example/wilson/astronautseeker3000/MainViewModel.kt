package com.example.wilson.astronautseeker3000

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.wilson.astronautseeker3000.model.Interaction
import com.example.wilson.astronautseeker3000.util.DelegatedBindable

class MainViewModel : BaseObservable() {

    @get:Bindable
    var winnerName: String by DelegatedBindable("", this)

    @get:Bindable
    var shouldRotate: Boolean by DelegatedBindable(false, this)

    fun handleInteraction(action: Interaction) {
        when (action) {
            is Interaction.Rotate -> triggerRotation()
            is Interaction.StopRotate -> updateWinnerMessage(action.winner)
        }
    }

    private fun triggerRotation() {
        shouldRotate = true
    }

    private fun updateWinnerMessage(winner: String) {
        winnerName = winner
        shouldRotate = false
    }
}