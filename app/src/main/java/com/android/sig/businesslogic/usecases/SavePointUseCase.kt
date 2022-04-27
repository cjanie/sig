package com.android.sig.businesslogic.usecases

import com.android.sig.TypeEnum
import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.exceptions.NoAvailableGeolocationException
import com.android.sig.businesslogic.exceptions.UndefinedTypeException
import com.android.sig.businesslogic.gateways.SavePointGateway
import kotlin.jvm.Throws

class SavePointUseCase(val savePointGateWay: SavePointGateway) {
    fun handle(latitude: Double?, longitude: Double?, name: String?, type: TypeEnum?, note: String?): Long {
        return this.savePointGateWay.save(this.createPoint(latitude, longitude, name, type, note))
    }

    @Throws(NoAvailableGeolocationException::class, UndefinedTypeException::class)
    private fun createPoint(latitude: Double?, longitude: Double?, name: String?, type: TypeEnum?, note: String?): Point {
        if(latitude == null || longitude == null)
            throw NoAvailableGeolocationException()
        if(type == null)
            throw UndefinedTypeException()
        return Point(null, latitude, longitude, name, type, note)
    }
}