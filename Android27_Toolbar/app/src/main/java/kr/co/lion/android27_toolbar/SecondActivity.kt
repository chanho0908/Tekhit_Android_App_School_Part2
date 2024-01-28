package kr.co.lion.android27_toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android27_toolbar.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}