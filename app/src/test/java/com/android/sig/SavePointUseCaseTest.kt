package com.android.sig

import com.android.sig.entities.Point
import com.android.sig.exceptions.NoAvailableGeolocationException
import com.android.sig.exceptions.UndefinedTypeException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class SavePointUseCase {
    fun handle(latitude: Double?, longitude: Double?, name: String?, type: TypeEnum?, note: String?): Point {
        return this.createPoint(latitude, longitude, name, type, note)
    }

    private fun createPoint(latitude: Double?, longitude: Double?, name: String?, type: TypeEnum?, note: String?): Point {
        if(latitude == null || longitude == null)
            throw NoAvailableGeolocationException()
        if(type == null)
            throw UndefinedTypeException()
        return Point(latitude, longitude, name, type, note)
    }
}

class SavePointUseCaseTest {

    @Test
    fun createsPointWhenGeolocationIsAvailable() {
        assert(SavePointUseCase().handle(1.2, 1.2, "Point A", TypeEnum.CASTEL, "Comment about Point A").latitude.equals(1.2))
    }

    @Test
    fun handlesErrorWhenNoAvailableGeolocation() {
        assertThrows<NoAvailableGeolocationException> { SavePointUseCase().handle(null, null, null, TypeEnum.CASTEL, null) }
    }

    @Test
    fun requiresTypeToBeDefined() {
        assertThrows<UndefinedTypeException> { SavePointUseCase().handle(1.2, 1.2, null, null, null) }
    }
}