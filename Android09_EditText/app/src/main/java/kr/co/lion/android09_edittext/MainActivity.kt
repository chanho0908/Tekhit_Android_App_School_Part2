package kr.co.lion.android09_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android09_edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //EditText
            editTextText.apply {
                // 문자열을 설정해준다.
                setText("코드로 설정한 문자열 입니다.")

                // 실시간으로 키보드 입력을 감사한다.

            }

            button.apply {
                setOnClickListener {
                    // EditText에서 문자열을 가져온다.
                    // Editable Type으로 반환 되므로 String 형태로 사용하고자 한다면 변환해줘야 한다.
                    val str1 = editTextText.text.toString()
                    textView.text = str1
                }
            }
        }
    }
}