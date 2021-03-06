package com.bd_drmwan.moviesapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bd_drmwan.common_extensions.makeStatusBarTransparent
import com.bd_drmwan.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
    }
}