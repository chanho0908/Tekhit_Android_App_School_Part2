package kr.co.lion.android26_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android26_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            // intent를 통해 전달된 데이터를 출력한다.
            textViewSecond.apply {
                // intent에 담겨서 전달된 데이터를 가져온다
                val data1 = intent?.getIntExtra("data1", 0)
                val data2 = intent?.getDoubleExtra("data2", 0.0)
                val data3 = intent?.getBooleanExtra("data3", false)

                text = "SecondActivity\n"
                append("data1 : ${data1}\n")
                append("data2 : ${data2}\n")
                append("data3 : $data3")
            }

            buttonFinishSecond.setOnClickListener {
                // 현재 Activity를 종료한다.
                // Activity가 종료되면 BackStack에서 Activity가 제거되고
                // 메모리에서도 제거된다 (완전 제거)
                finish()
            }
        }
    }
}