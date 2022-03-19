package com.android.sig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class RecordViewModel: ViewModel() {

    private val _longitude = MutableLiveData<Double>()
    val longitude: LiveData<Double> = _longitude

    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double> = _latitude

    private val _pointName = MutableLiveData<String>()
    val pointName: LiveData<String> = _pointName

    private val _type = MutableLiveData<String>()
    val type: LiveData<String> = _type

    private val _note = MutableLiveData<String>()
    val note: LiveData<String> = _note

    init {
        this.resetRecord()
    }

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

    fun resetRecord() {
        this._longitude.value = null
        this._latitude.value = null
        this._pointName.value = ""
        this._type.value = TypeOptionEnum.UNDEFINED.toString()
        this._note.value = ""
    }

}