package com.android.sig.ui

import com.android.sig.R
import com.android.sig.businesslogic.enums.TypeEnum
import com.android.sig.businesslogic.enums.TypeVisitor

class TypeRadioButtonHandler() {

    private val visitor = object : TypeVisitor<Int> {
        override fun visitRuin(): Int {
            return R.id.ruin
        }

        override fun visitCastel(): Int {
            return R.id.castel
        }

        override fun visitWall(): Int {
            return R.id.wall
        }

        override fun visitHistoricSite(): Int {
            return R.id.historic
        }

        override fun visitArchaeologicSite(): Int {
            return R.id.archaeologic
        }

        override fun visitOtherType(): Int {
            return R.id.other_type
        }
    }

    fun getRadioButtonId(type: TypeEnum): Int {
        return type.accept(visitor)
    }
}