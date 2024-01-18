package kr.co.lion.android09_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
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

                // 문자열을 입력할 때 마다 동작한다.
                // it : EditText에 입력된 문자열을 가지고 온다.
                addTextChangedListener {
                    textView.text = "실시간 감시 : $it"
                }

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