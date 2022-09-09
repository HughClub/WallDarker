package com.github.hughnew.autodark

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity


abstract class AutoBaseActivity : AppCompatActivity(), UiModeListener {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> { onDarkMode() }
            Configuration.UI_MODE_NIGHT_NO -> { onNormalMode() }
        }
    }
}