package com.android.businesslogic.domain.entities

import com.android.businesslogic.domain.enums.TypeEnum

class Point(
    val id: Long?,
    val latitude: Double,
    val longitude: Double,
    val name: String?,
    val type: TypeEnum?,
    val note: String?
)