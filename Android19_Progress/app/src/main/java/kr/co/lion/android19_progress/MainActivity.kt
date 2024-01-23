package kr.co.lion.android19_progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android19_progress.databinding.ActivityMainBinding
// [ LinearProgressIndicator ]
// 1. 주요 속성
// - indeterminate : true로 주면 애니메이션이 계속 나오는 상태로 보여진다.
//
// 2. 주요 프로퍼티
// - progress : 현재 진행바의 크기를 설정해준다. 최소0, 최대 100
//
// 3. 주요 메서드
// - setProgress : 현재 진행바의 크기를 설정해준다. 두 번째 매개변수에 true를 넣어주면 애니메이션 효과가 나타난다.
// [ Slider ]
// 1. 주요 속성
// - valueFrom : 최소 값
// - valueTo : 최대 값
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener {
                // progressIndicator의 값을 설정한다.
                //progressBar.progress += 10
                progressBar.setProgress(progressBar.progress + 10, true)
            }

            button2.setOnClickListener {
                textView.text = "progress value : ${progressBar.progress}"
            }

            button3.setOnClickListener {
                slider.value = 80.0f
            }

            button4.setOnClickListener {
                textView.text = "slider value : ${slider.value}"
            }
        }

    }
}