package com.android.sig.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import kotlin.jvm.Throws

class ImageTransformer {

    @Throws(DivisionByZeroException::class)
    fun getSmallerImage(drawable: Drawable, scale: Int): Bitmap {
        if(scale == 0)
            throw DivisionByZeroException()

        val image: Bitmap = (drawable as BitmapDrawable).bitmap
        return Bitmap.createScaledBitmap(
            image,
            image.width/scale,
            image.height/scale,
            false
        )
    }
}