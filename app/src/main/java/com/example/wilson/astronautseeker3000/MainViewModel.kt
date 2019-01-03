package com.example.wilson.astronautseeker3000

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.wilson.astronautseeker3000.model.DelegatedBindable
import com.example.wilson.astronautseeker3000.model.PlayerInteraction

class MainViewModel : BaseObservable() {

    @get:Bindable
    var winnerName: String by DelegatedBindable("", this)

    @get:Bindable
    var triggerRotation: Boolean by DelegatedBindable(false, this)

    fun handlePlayerInteraction(action: PlayerInteraction) {
        when (action) {
            is PlayerInteraction.Rotate -> triggerRotation()
            is PlayerInteraction.StopRotate -> updateWinner(action.winner)
        }
    }

    private fun triggerRotation() {
        triggerRotation = true
    }

    private fun updateWinner(winner: String) {
        winnerName = winner
        triggerRotation = false
    }
}