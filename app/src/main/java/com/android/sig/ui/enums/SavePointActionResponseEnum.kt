package com.android.sig.ui.enums

enum class SavePointActionResponseEnum {

    SUCCESS {
        override fun accept(visitor: SavePointActionResponseVisitor) {
            return visitor.visitSuccess()
        }
    },
    NO_AVAILABLE_GEOLOCATION_ERROR {
        override fun accept(visitor: SavePointActionResponseVisitor) {
            return visitor.visitNoAvailableGeolocationError()
        }
    },
    MISSING_TYPE_ERROR {
        override fun accept(visitor: SavePointActionResponseVisitor) {
            return visitor.visitMissingTypeError()
        }

    };

    abstract fun accept(visitor: SavePointActionResponseVisitor)
}