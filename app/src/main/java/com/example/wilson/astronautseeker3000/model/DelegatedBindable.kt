package com.example.wilson.astronautseeker3000.model

import android.databinding.BaseObservable
import com.example.wilson.astronautseeker3000.BR
import kotlin.reflect.KProperty

class DelegatedBindable<T>(
    private var value: T,
    private val observer: BaseObservable
) {

    private var propertyId: Int = -1

    operator fun getValue(thisRef: Any?, p: KProperty<*>) = value

    operator fun setValue(thisRef: Any?, p: KProperty<*>, v: T) {
        value = v
        observer.notifyPropertyChanged(getPropertyId(p))
    }

    private fun getPropertyId(p: KProperty<*>): Int {
        if (propertyId == -1) {
            propertyId = BR::class.java.fields.first { it.name == p.name }.getInt(null)
        }
        return propertyId
    }
}