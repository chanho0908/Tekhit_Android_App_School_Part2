package com.myproject.cloudbridge.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.cloudbridge.R
import com.myproject.cloudbridge.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            materialToolbar.setNavigationOnClickListener {
                // 현재 Activity를 종료시킨다.
                finish()
            }
        }
    }
}