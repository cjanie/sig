package com.android.sig

import com.android.sig.adapters.InMemorySavePointGateWayImpl
import com.android.sig.businesslogic.exceptions.NoAvailableGeolocationException
import com.android.sig.businesslogic.exceptions.UndefinedTypeException
import com.android.sig.businesslogic.usecases.SavePointUseCase
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class SavePointUseCaseTest {

    @Test
    fun createsPointWhenSuccess() {
        assert(SavePointUseCase(InMemorySavePointGateWayImpl()).handle(1.2, 1.2, "Point A", TypeEnum.CASTEL, "Comment about Point A").equals(1L))
    }

    @Test
    fun handlesErrorWhenNoAvailableGeolocation() {
        assertThrows<NoAvailableGeolocationException> { SavePointUseCase(InMemorySavePointGateWayImpl()).handle(null, null, null, TypeEnum.CASTEL, null) }
    }

    @Test
    fun requiresTypeToBeDefined() {
        assertThrows<UndefinedTypeException> { SavePointUseCase(InMemorySavePointGateWayImpl()).handle(1.2, 1.2, null, null, null) }
    }
}