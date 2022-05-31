package com.android.businesslogic

import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.usecases.exceptions.NoAvailableGeolocationException
import com.android.businesslogic.usecases.exceptions.MissingTypeException
import com.android.businesslogic.usecases.SavePointUseCase
import com.android.businesslogic.inmemoryadapters.InMemoryPointCommandGatewayImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SavePointUseCaseTest {

    @Test
    fun createsPointWhenSuccess() = runBlocking {
        val id: Long = SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(
            1.2,
            1.2,
            "Point A",
            TypeEnum.CASTEL,
            "Comment about Point A")
        assert(id.equals(1L))
    }

    @Test(expected = NoAvailableGeolocationException::class)
    fun handlesErrorWhenNoAvailableGeolocation() = runBlocking {
        val id = SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(
            null,
            null,
            null,
            TypeEnum.CASTEL,
            null)
    }

    @Test(expected = MissingTypeException::class)
    fun requiresTypeToBeDefined() = runBlocking {
        val id = SavePointUseCase(InMemoryPointCommandGatewayImpl()).handle(
            1.2,
            1.2,
            null,
            null,
            null)
    }

}