package kr.co.lion.android07_textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android07_textview.databinding.ActivityMainBinding

//[ TextView ]
//text : TextView를 통해 보여줄 문자열을 설정한다.
//lines : 최대 줄 수. 생략하면 무제한. 매우 긴 문자열을 설정하였을 경우 lines에 설정되어 있는 줄 수 만큼만 보이고 나머지는 짤린다.
//textAppearance : 표시되는 문자열에 관련된 다양한 속성들을 미리 설정해 놓은 것
//fontFamily : 폰트
//typeface : 글자 하나가 보여지는 형식
//textSize : sp 단위의 글자크기
//lineSpacingExtra : 줄 간격
//textColor : 표시되는 문자열 색상
//textStyle : 볼드, 이탤릭, 전부 대문자
//textAlignment : 문자열 정렬
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // TextView의 프로퍼티와 메서드 사용
            textView.apply {
                text = "새로운 문자열\n"
                // 문자열을 추가한다.
                // \n : 아래로 내린다는 의미의 글자

                append("문자열2\n")
                append("문자열3\n")
            }
        }
    }
}