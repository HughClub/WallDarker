package com.github.hughnew.autodark

import android.app.WallpaperManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.lifecycle.ViewModelProvider
import com.github.hughnew.autodark.databinding.ActivityMainBinding

class MainActivity : AutoBaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val imageSelector = registerForActivityResult(OpenPicture()) { data ->
        data.uri?.let {
            vm.setImage(it, data.type)
            val iv = if (data.type.isDark()) {
                binding.ivNight
            } else binding.ivLight
            iv.setImageURI(it)
            with(getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager) {
                //
            }
        }
    }
    private val width by lazy { getScreenWidth() }
    private val height by lazy { getScreenHeight() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        resources.configuration.uiMode = resources.configuration.uiMode

        binding.ivLight.setOnLongClickListener {
            Toast.makeText(this,
                "dark:${resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES}",
                Toast.LENGTH_SHORT
            ).show()
            true
        }
        resizeImageView()
        binding.ivLight.setOnClickListener {
            imageSelector.launch(MainViewModel.ShowType.Light)
        }
        binding.ivNight.setOnClickListener {
            imageSelector.launch(MainViewModel.ShowType.Night)
        }
    }

    override fun onDarkMode() {
        binding.ivLight.offShow()
        binding.ivNight.onShow()
        binding.root.setBackgroundColor(resources.getColor(R.color.black))
    }

    override fun onNormalMode() {
        binding.ivLight.onShow()
        binding.ivNight.offShow()
        binding.root.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun resizeImageView() {
        binding.ivLight.resize(width.fit, height.fit)
        binding.ivNight.resize(width.fit, height.fit)
        binding.switcher.margin(bottom = width.fit/2)
    }
    companion object {
        const val IV_RATIO = 0.4
        val Int.fit get() = (this * IV_RATIO).toInt()
    }
}