package com.github.hughnew.autodark

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.core.content.edit
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val light: MutableList<Uri?> = mutableListOf(null, null)
    private val night: MutableList<Uri?> = mutableListOf(null, null)
    private var readExternal: Boolean = false
    fun loadData(context: Context) = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        .apply {
            readExternal = context.hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    fun saveData(context: Context) = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        .edit {
//            putString("light_lock",)
        }
    fun setImage(uri: Uri, type: ShowType) {
        when (type) {
            ShowType.All -> {
                light[0] = uri
                light[1] = uri
                night[0] = uri
                night[1] = uri
            }
            ShowType.Light -> {
                light[0] = uri
                light[1] = uri
            }
            ShowType.Night -> {
                night[0] = uri
                night[1] = uri
            }
            ShowType.LightLock -> {
                light[0] = uri
            }
            ShowType.LightHome -> {
                light[1] = uri
            }
            ShowType.NightLock -> {
                night[0] = uri
            }
            ShowType.NightHome -> {
                night[1] = uri
            }
        }
    }
    fun getImage(type: ShowType): Uri? {
        return when (type) {
            ShowType.LightLock -> light[0]
            ShowType.LightHome -> light[1]
            ShowType.NightLock -> night[0]
            ShowType.NightHome -> night[1]
            ShowType.All, ShowType.Light, ShowType.Night -> null
        }
    }
    enum class ShowType(value: Int) {
        All(0),
        Light(1),
        Night(2),
        LightLock(3),
        LightHome(4),
        NightLock(5),
        NightHome(6);
        fun isDark(): Boolean =
            when (this) {
                All, Night, NightLock, NightHome -> true
                else -> false
            }
    }
    companion object {
        const val TAG = "MainViewModel"
        const val SHARED = "dark_share"
    }
}