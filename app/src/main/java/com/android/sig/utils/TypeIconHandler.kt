package com.android.sig.utils

import com.android.sig.R
import com.android.sig.businesslogic.enums.TypeEnum
import com.android.sig.businesslogic.enums.TypeVisitor

class TypeIconHandler {

    fun getIconId(type: TypeEnum): Int {

        val visitor = object : TypeVisitor<Int> {
            override fun visitRuin(): Int {
                return R.drawable.ruin
            }

            override fun visitCastel(): Int {
                return R.drawable.castel
            }

            override fun visitWall(): Int {
                return R.drawable.wall
            }

            override fun visitHistoricSite(): Int {
                return R.drawable.historic_site
            }

            override fun visitArchaeologicSite(): Int {
                return R.drawable.archaeologic_site
            }

            override fun visitOtherType(): Int {
                return R.drawable.hydraulic_monument
            }

        }

        return type.accept(visitor)
    }
}