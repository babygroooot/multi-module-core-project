package com.madskill.mad_skill

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.core.common.util.viewBinding
import com.madskill.mad_skill.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
