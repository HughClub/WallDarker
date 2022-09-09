package com.github.hughnew.autodark

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.github.hughnew.autodark.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AutoBaseActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        resources.configuration.uiMode = resources.configuration.uiMode

        binding.ivLight.setOnClickListener {
            Toast.makeText(this,
                "dark:${resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES}",
                Toast.LENGTH_SHORT
            ).show()
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

}