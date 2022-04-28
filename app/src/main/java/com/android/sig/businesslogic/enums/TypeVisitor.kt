package com.android.sig.businesslogic.enums

interface TypeVisitor<T> {

    fun visitRuin(): T
    fun visitCastel(): T
    fun visitWall(): T
    fun visitHistoricSite(): T
    fun visitArchaeologicSite(): T
    fun visitOtherType(): T

}