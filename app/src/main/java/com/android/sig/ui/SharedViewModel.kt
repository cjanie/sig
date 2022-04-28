package com.android.sig.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sig.businesslogic.enums.TypeEnum
import com.android.sig.businesslogic.exceptions.NoAvailableGeolocationException
import com.android.sig.businesslogic.exceptions.UndefinedTypeException

import com.android.sig.businesslogic.usecases.SavePointUseCase


class SharedViewModel(val savePointUseCase: SavePointUseCase): ViewModel() {

    private val _longitude = MutableLiveData<Double>()
    val longitude: LiveData<Double> = _longitude

    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double> = _latitude

    private val _pointName = MutableLiveData<String>()
    val pointName: LiveData<String> = _pointName

    private val _type = MutableLiveData<TypeEnum>()
    val type: LiveData<TypeEnum> = _type

    private val _note = MutableLiveData<String>()
    val note: LiveData<String> = _note

    init {
        this.reset()
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

    fun setType(type: TypeEnum) {
        this._type.value = type
    }

    fun setNote(note: String) {
        this._note.value = note
    }


    fun reset() {
        this._longitude.value = null
        this._latitude.value = null
        this._pointName.value = ""
        this._type.value = null
        this._note.value = ""
    }

    @Throws(
        NoAvailableGeolocationException::class,
        UndefinedTypeException::class
    )
    fun savePoint() {
        this.savePointUseCase.handle(
            this._latitude.value,
            this._longitude.value,
            this._pointName.value,
            this._type.value,
            this._note.value
        )
    }

}