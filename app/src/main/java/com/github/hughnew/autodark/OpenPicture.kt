package com.github.hughnew.autodark

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper

open class OpenPicture : ActivityResultContract<MainViewModel.ShowType, OpenPicture.TypedUri>() {
    private var input: MainViewModel.ShowType = MainViewModel.ShowType.All
    @CallSuper
    override fun createIntent(context: Context, input: MainViewModel.ShowType): Intent {
        this.input = input
        return Intent(Intent.ACTION_PICK)
            .setType("image/*")
    }

    final override fun getSynchronousResult(
        context: Context,
        input: MainViewModel.ShowType
    ): SynchronousResult<TypedUri>? = null

    final override fun parseResult(resultCode: Int, intent: Intent?): TypedUri {
        return TypedUri(input, intent.takeIf { resultCode == Activity.RESULT_OK }?.data)
    }
    data class TypedUri(val type: MainViewModel.ShowType, val uri: Uri?)
}