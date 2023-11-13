package com.example.sebbiatest.ui.activities

import com.example.sebbiatest.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sebbiatest.app.App
import com.example.sebbiatest.databinding.ActivityMainBinding
import com.example.sebbiatest.databinding.FragmentNewsCategoryBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}