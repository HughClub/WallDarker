package com.github.hughnew.autodark

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.edit
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val light: List<Uri> = listOf()
    private val night: List<Uri> = listOf()
    private var readExternal: Boolean = false
    fun loadData(context: Context) = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        .apply {
            readExternal = context.hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    fun saveData(context: Context) = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        .edit {
//            putString("light_lock",)
        }
    fun getImageIntent(): Intent = Intent(Intent.ACTION_PICK).apply {
        type = "image/*"
    }
    companion object {
        const val TAG = "MainViewModel"
        const val SHARED = "dark_share"
    }
}