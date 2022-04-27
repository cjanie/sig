package com.android.sig.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.sig.businesslogic.usecases.SavePointUseCase
import java.lang.IllegalArgumentException

class SharedViewModelFactory(private val savePointUseCase: SavePointUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedViewModel::class.java))
            return SharedViewModel(this.savePointUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}