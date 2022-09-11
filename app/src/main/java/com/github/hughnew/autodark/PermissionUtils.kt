package com.github.hughnew.autodark

import android.content.Context
import android.content.pm.PackageManager

fun Context.hasPermission(permission: String) =
    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED