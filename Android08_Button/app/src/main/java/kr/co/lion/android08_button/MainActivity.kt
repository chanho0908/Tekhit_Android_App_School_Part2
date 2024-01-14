package kr.co.lion.android08_button

import android.opengl.GLES30
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.co.lion.android08_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button2.apply {
                // 보통 리스너는 다수의 뷰가 하나의 리스너를 사용하는 경우는 별로 없다.
                // 작성한 리스너가 하나의 뷰에 대한 리스너라면 중첩클래스를 사용하기도 한다.
                setOnClickListener {

                    textView.apply {
                        text = "두 번째 버튼을 눌렀습니다."
                    }

                }
            }

            button3.apply {
                // 코틀린으로 작업하는 경우
                // 구현해야할 메서드가 하나밖에 없는 리스너에 대해서는
                // 고차함수도 제공하고 있다.
                setOnClickListener {
                    textView.apply {
                        text = "첫 번째 버튼을 눌렀습니다."
                    }
                }
            }
        }
    }

    inner class ButtonClickListener: OnClickListener{
        override fun onClick(v: View?) {
            binding.apply {
                textView.apply {  "text = "  }
            }
        }

    }
}