package kr.co.lion.android30_thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kr.co.lion.android30_thread.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thread {
            while(true){
                // 쓰래드를 발생시킨다.
                thread {
                    while(true){
                        SystemClock.sleep(100)
                        val now = System.currentTimeMillis()
                        Log.d("test1234", "Thread1 - $now")

                        runOnUiThread {

                        }
                    }
                }

                thread{
                    while(true){
                        SystemClock.sleep(100)
                        val now = System.currentTimeMillis()
                        Log.d("test1234", "Thread2 - $now")
                    }
                }

                binding.apply {
                    button.setOnClickListener {
                        val now = System.currentTimeMillis()
                        textView2.text = "버튼 클릭 : $now"
                    }
                }
            }
        }
    }


}