package kr.co.lion.android23_materialetc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android23_materialetc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}