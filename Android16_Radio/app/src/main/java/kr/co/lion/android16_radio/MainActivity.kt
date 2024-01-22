package kr.co.lion.android16_radio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android16_radio.databinding.ActivityMainBinding
// [ RadioGroup]
// 1. 주요 속성
// - checkedButton : 그룹내에서 선택되어 있을 라디오 버튼의 아이디를 지정한다.
//
// 2. 주요 리스너
// - setOnCheckedChangeListener : 그룹 내의 라디오 버튼 체크 상태가 변경될 때
// 3. 주요 프로퍼티
// - checkedRadioButtonId : 그룹내에서 선택되어 있는 라디오 버튼의 아이디
//
// [ RadioButton ]
// 1. 주요 프로퍼티
//- isChecked : 체크 상태를 설정하거나 체크 상태를 가져올 때
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            // 두 번째 그룹의 라디오 버튼을 선택해준다.
            // 라디오 버튼 선택을 그룹을 통하지 않고 원하는 라디오 버튼에 직접 설정한다.
            radioButton5.isChecked = true

            // 라디오 그룹의 리스너
            // 그룹 내의 체크 상태가 변경 되었을때
            // 두 번째 : 체크가 된 라디오 버튼의 아이디
            group1.setOnCheckedChangeListener { group, checkedId ->
                textView.text = when(checkedId){
                    R.id.radioButton -> "첫 번째 라디오 버튼 선택"
                    R.id.radioButton2 -> "두 번째 라디오 버튼 선택"
                    R.id.radioButton3 -> "세 번째 라디오 버튼 선택"
                    else -> ""
                }
            }

            button.setOnClickListener {
                // 그룹 내에서 체크되어 있는 라디오 버튼의 id로 분기한다.
                textView.text = when(group2.checkedRadioButtonId){
                    R.id.radioButton4 -> "네 번째 라디오 버튼 선택"
                    R.id.radioButton5 -> "다섯 번째 라디오 버튼 선택"
                    R.id.radioButton6 -> "여섯 번째 라디오 버튼 선택"
                    else -> ""
                }
            }
        }
    }
}