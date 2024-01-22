package kr.co.lion.android18_switch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android18_switch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            button.setOnClickListener {
                textView.text = when(switch1.isChecked){
                    true -> "switch1 은 on 상태입니다"
                    false -> "switch1 는 off 상태입니다"
                }
            }

            button2.setOnClickListener {
                // on / off 상태를 설정
                switch1.isChecked = false
                switch2.isChecked = true
            }

            button3.setOnClickListener {
                // on / off 상태를 반전시킨다.
                switch1.toggle()
                switch2.toggle()
            }

            // 스위치의 on/off 상태가 변경되었을 때
            // 두 번째 : 설정된 on/off 상태가 전달된다.
            switch1.setOnCheckedChangeListener { v, isChecked ->
                textView.text = when(isChecked){
                    true -> "switch1 이 on 상태가 되었습니다"
                    false -> "switch1 이 off 상태가 되었습니다."
                }
            }
        }
    }
}