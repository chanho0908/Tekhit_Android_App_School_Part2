package kr.co.lion.android29_message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android29_message.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.text = intent.getStringExtra("KEY_DATA")
    }


}