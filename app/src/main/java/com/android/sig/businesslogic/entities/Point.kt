package com.android.sig.businesslogic.entities

import com.android.sig.TypeEnum

class Point(
    val id: Long?,
    val latitude: Double,
    val longitude: Double,
    val name: String?,
    val type: TypeEnum?,
    val note: String?
)