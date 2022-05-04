package com.android.infrastructure

import android.app.Application
import android.content.Context
import com.android.businesslogic.gateways.PointCommandGateway
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.infrastructure.adapters.PointCommandGatewayImpl
import com.android.infrastructure.adapters.PointQueryGatewayImpl

import com.android.infrastructure.database.LocalDatabase

class DI(private val application: Application) {

    private val _database by lazy { LocalDatabase.getDatabase(this.application) }

    private val pointCommandGateway by lazy { PointCommandGatewayImpl(this._database.pointDao()) }

    private val pointQueryGateway by lazy { PointQueryGatewayImpl() }

    fun pointCommandGateway() : PointCommandGateway {
        return this.pointCommandGateway
    }

    fun pointQueryGateway(): PointQueryGateway {
        return this.pointQueryGateway
    }
}