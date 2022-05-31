package com.android.sig.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.usecases.exceptions.NoAvailableGeolocationException
import com.android.businesslogic.usecases.exceptions.MissingTypeException

import com.android.businesslogic.usecases.SavePointUseCase
import com.android.sig.ui.enums.SavePointActionResponseEnum
import kotlinx.coroutines.launch


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

    private val _savePointActionResponse = MutableLiveData<SavePointActionResponseEnum>()
    val savePointActionResponse = _savePointActionResponse

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
        this._savePointActionResponse.value = null
    }

    fun resetSavePointActionResponse() {
        this._savePointActionResponse.value = null
    }

     fun savePoint() {
        this.viewModelScope.launch {
            try {
                savePointUseCase.handle(
                    _latitude.value,
                    _longitude.value,
                    _pointName.value,
                    _type.value,
                    _note.value
                )
                _savePointActionResponse.value = SavePointActionResponseEnum.SUCCESS
            } catch (e: MissingTypeException) {
                e.printStackTrace()
                _savePointActionResponse.value = SavePointActionResponseEnum.MISSING_TYPE_ERROR
            } catch (e: NoAvailableGeolocationException) {
                _savePointActionResponse.value = SavePointActionResponseEnum.NO_AVAILABLE_GEOLOCATION_ERROR
            }
        }
    }

}