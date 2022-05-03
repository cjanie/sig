package com.android.sig

import com.android.sig.adapters.InMemoryPointCommandGatewayImpl
import com.android.sig.businesslogic.enums.TypeEnum
import com.android.sig.businesslogic.exceptions.NoAvailableGeolocationException
import com.android.sig.businesslogic.exceptions.UndefinedTypeException
import com.android.sig.businesslogic.usecases.SavePointUseCase
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class SavePointUseCaseTest {

    @Test
    fun createsPointWhenSuccess() {
        assert(SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(1.2, 1.2, "Point A", TypeEnum.CASTEL, "Comment about Point A").equals(1L))
    }

    @Test
    fun handlesErrorWhenNoAvailableGeolocation() {
        assertThrows<NoAvailableGeolocationException> { SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(null, null, null, TypeEnum.CASTEL, null) }
    }

    @Test
    fun requiresTypeToBeDefined() {
        assertThrows<UndefinedTypeException> { SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(1.2, 1.2, null, null, null) }
    }
}