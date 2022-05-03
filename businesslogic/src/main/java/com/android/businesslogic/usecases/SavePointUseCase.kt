package com.android.businesslogic.usecases

import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.usecases.exceptions.NoAvailableGeolocationException
import com.android.businesslogic.usecases.exceptions.UndefinedTypeException
import com.android.businesslogic.gateways.PointCommandGateway
import kotlin.jvm.Throws

class SavePointUseCase(val pointCommandGateWay: PointCommandGateway) {
    fun handle(latitude: Double?, longitude: Double?, name: String?, type: TypeEnum?, note: String?): Long {
        return this.pointCommandGateWay.save(this.createPoint(latitude, longitude, name, type, note))
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