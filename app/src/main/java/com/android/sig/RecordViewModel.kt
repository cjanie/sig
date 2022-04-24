package com.android.sig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sig.entities.Geolocation
import com.android.sig.entities.Point
import com.android.sig.exceptions.HasNoNameSetException
import kotlin.jvm.Throws


class RecordViewModel: ViewModel() {

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

    fun setType(type: TypeEnum) {
        this._type.value = type
    }

    fun setNote(note: String) {
        this._note.value = note
    }


    fun resetRecord() {
        this._longitude.value = null
        this._latitude.value = null
        this._pointName.value = ""
        this._type.value = null
        this._note.value = ""
    }

    @Throws(HasNoNameSetException::class)
    fun saveRecord() {
        if(this._pointName.value.isNullOrEmpty())
            throw HasNoNameSetException();
        if(this._longitude.value != null && this._latitude.value != null) {
            val point: Point = Point(
                    Geolocation(this._longitude.value!!, this._latitude.value!!),
                    this._pointName.value!!,
                    this._type.value,
                    this._note.value
            )
            System.out.println("Point saved: " + point.geolocation.latitude + " " + point.geolocation.longitude + " " + point.name + " " + point.type + " " + point.note)
        }

    }

}