package com.android.sig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue

class RecordViewModel: ViewModel() {

    private val latitude = MutableLiveData<Double>()

    private val longitude = MutableLiveData<Double>()

    private val pointName = MutableLiveData<String>()

    private val type = MutableLiveData<String>()

    private val note = MutableLiveData<String>()

    fun setLatitude(latitude: Double) {
        this.latitude.value = latitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude.value = longitude
    }

    fun setPointName(pointName: String) {
        this.pointName.value = pointName
    }

    fun setType(type: String) {
        this.type.value = type
    }

    fun setNote(note: String) {
        this.note.value = note
    }
}