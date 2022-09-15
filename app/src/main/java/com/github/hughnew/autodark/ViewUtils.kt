package com.github.hughnew.autodark

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Px
import androidx.core.view.marginStart
import kotlin.math.roundToInt

inline val Number.dpFloat
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    )
inline val Number.dp
    get() = dpFloat.roundToInt()
private const val ElevationDp = 42
const val IN_BEHIND = 0.5f
val ELEVATION_PX = ElevationDp.dpFloat

fun ImageView.onShow() {
    elevation = ELEVATION_PX
    alpha = 1f
}

fun ImageView.offShow() {
    elevation = 0f
    alpha = IN_BEHIND
}

fun View.resize(w: Int, h: Int) {
    layoutParams = layoutParams.apply {
        width = w
        height = h
    }
}

fun ViewGroup.MarginLayoutParams.margin(
    @Px start: Int? = null,
    @Px top: Int? = null,
    @Px end: Int? = null,
    @Px bottom: Int? = null,
) {
    start?.let { marginStart = it }
    end?.let { marginEnd = it }
    top?.let { topMargin = it }
    bottom?.let { bottomMargin = it }
}

fun View.margin(
    @Px start: Int? = null,
    @Px top: Int? = null,
    @Px end: Int? = null,
    @Px bottom: Int? = null,
) {
    val lp = layoutParams
    if (lp is ViewGroup.MarginLayoutParams) {
        lp.margin(start, top, end, bottom)
    }
    layoutParams = lp
}

// region screen metrics
fun Activity.getMetrics(): List<Int> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        with(windowManager.currentWindowMetrics) {
            listOf(bounds.width(), bounds.height())
        }
    } else {
        with(DisplayMetrics()) {
            windowManager.defaultDisplay.getMetrics(this)
            listOf(this.widthPixels, this.heightPixels)
        }
    }

fun Activity.getScreenWidth(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        with(windowManager.currentWindowMetrics) {
            bounds.width()
        }
    } else {
        with(DisplayMetrics()) {
            windowManager.defaultDisplay.getMetrics(this)
            this.widthPixels
        }
    }

fun Activity.getScreenHeight(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        with(windowManager.currentWindowMetrics) {
            bounds.height()
        }
    } else {
        with(DisplayMetrics()) {
            windowManager.defaultDisplay.getMetrics(this)
            this.heightPixels
        }
    }
// endregion screen metrics
