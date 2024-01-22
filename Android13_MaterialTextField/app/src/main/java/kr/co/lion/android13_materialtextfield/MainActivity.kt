package kr.co.lion.android13_materialtextfield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android13_materialtextfield.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
//[ TextInputLayout ]
//1. 주요 속성
//- hint : 입력 안내 문구를 표시한다
//- endIconMode : 우측에 표시되는 아이콘을 설정한다.
//- startIconDrawable : 좌측에 표시되는 아이콘을 설정한다.
//
//[ TextInputEditText] (EditText에 있는 것은 모두 설정가능하다)
//- hint : 입력 안내 문구를 표시한다. Material 3 의 문서에는 TextInputLayout에 설정하는 것으로 되어 있다
//- text : EditText 를 통해 보여줄 문자열
//- inputType : EditText로 부터 입력받을 문자열의 속성을 설정한다. 키보드에도 영향을 미친다
//- imeOptions : 키보드의 리턴 버튼의 모양을 설정한다.
//  설정하지 않을 경우 제일 마지막에 있는 EditText는 키보드를 내리는 키로 설정되고 그 외에는 다음 입력
//  요소로 포커스 이동하는 키로 설정된다.
//
//2. 주요 프로퍼티
//- text : 입력한 문자열을 가지고 온다.
//
//3. 주요 메서드
//- setText : 문자열을 설정한다.
//
//4. 주요 리스너
//- TextWatcher : 키보드 입력을 실시간으로 감지한다.
//
//            // error 설정
//            textField.error = "입력 오류가 발생하였습니다"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    activityMainBinding.apply {
        // 버튼을 눌렀을 때 입력한 내용을 가져와 출력한다.
        button.setOnClickListener {
            val str1 = textField.text.toString()
            //textView.text = str1
        }

        // error 설정
        // textField.error = "입력 오류가 발생하였습니다"
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = "입력 오류가 발생하였습니다"
    }
    }
}