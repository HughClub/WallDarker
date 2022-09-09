package com.github.hughnew.autodark

import android.content.res.Resources
import android.util.TypedValue
import android.widget.ImageView
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