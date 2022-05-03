package com.android.businesslogic

import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.usecases.exceptions.NoAvailableGeolocationException
import com.android.businesslogic.usecases.exceptions.UndefinedTypeException
import com.android.businesslogic.usecases.SavePointUseCase
import com.android.businesslogic.inmemoryadapters.InMemoryPointCommandGatewayImpl
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