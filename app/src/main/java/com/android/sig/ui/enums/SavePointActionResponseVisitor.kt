package com.android.sig.ui.enums

interface SavePointActionResponseVisitor {

    fun visitSuccess()
    fun visitMissingTypeError()
    fun visitNoAvailableGeolocationError()
}
