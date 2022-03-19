package com.android.sig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue

class RecordViewModel: ViewModel() {

    private val _latitude = MutableLiveData<Double>()

    private val _longitude = MutableLiveData<Double>()

    private val _pointName = MutableLiveData<String>()

    private val _type = MutableLiveData<String>()

    private val _note = MutableLiveData<String>()

    fun setLatitude(latitude: Double) {
        this._latitude.value = latitude
    }

    fun setLongitude(longitude: Double) {
        this._longitude.value = longitude
    }

    fun setPointName(pointName: String) {
        this._pointName.value = pointName
    }

    fun setType(type: String) {
        this._type.value = type
    }

    fun setNote(note: String) {
        this._note.value = note
    }

    fun hasNoNameSet(): Boolean {
        return _pointName.value.isNullOrEmpty()
    }
}