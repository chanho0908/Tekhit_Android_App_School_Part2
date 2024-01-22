package kr.co.lion.android17_checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.android17_checkbox.databinding.ActivityMainBinding

// [ CheckBox ]
// 1. 주요 속성
// - checked : true로 설정하면 체크 되어 있는 상태가 되고 false를 설정하거나 삭제하면 체크되어 있지
//  않는 상태가 된다.
// - checkedState : Material 3 에 추가된 속성. 체크 상태 3단계로 나눠서 설정할 수 있다.
//  checked : 체크된 상태
//  unchecked : 체크 안되 상태
//  indeterminate : - 로 표시되며 체크 박스들 중 선택 안된게 있을 경우 표시하는 용도로 사용한다.
//
// 2. 주요 프로퍼티
// - isChecked : 체크 여부를 설정한다 true/false
// - checkedState : MaterialCheckBox의 것이며 3가지 값 중 하나를 설정하여 체크 상태를 설정한다.
//  STATE_UNCHECKED : 체크된 상태
//  STATE_UNCHECKED : 체크 안되 상태
//  STATE_INDETERMINATE : - 로 표시되며 체크 박스들 중 선택 안된게 있을 경우 표시하는 용도로 사용한다.
//
// 3. 주요 리스너
// - setOnCheckedChangeListener : 체크 여부가 변경되었을 때
// - addOnCheckedStateChangedListener : 체크 상태가 변경되었을 때(MaterialCheckBox 것)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener {
                val a1 = getCheckedString(checkBox.isChecked)
                val a2 = getCheckedState((checkBox as MaterialCheckBox).checkedState)
                textView.text = "check1 checked : ${a1}\n"
                textView.append("check1 state : ${a2}\n")

                val a3 = getCheckedString(checkBox2.isChecked)
                val a4 = getCheckedState((checkBox2 as MaterialCheckBox).checkedState)
                textView.append("check2 checked : ${a3}\n")
                textView.append("check2 state : ${a4}\n")

                val a5 = getCheckedString(checkBox3.isChecked)
                val a6 = getCheckedState((checkBox3 as MaterialCheckBox).checkedState)
                textView.append("check3 checked : ${a5}\n")
                textView.append("check3 state : ${a6}\n")

                val a7 = getCheckedString(checkBox4.isChecked)
                val a8 = getCheckedState((checkBox4 as MaterialCheckBox).checkedState)
                textView.append("check4 checked : ${a7}\n")
                textView.append("check4 state : ${a8}\n")

                val a9 = getCheckedString(checkBox5.isChecked)
                val a10 = getCheckedState((checkBox5 as MaterialCheckBox).checkedState)
                textView.append("check4 checked : ${a9}\n")
                textView.append("check4 state : ${a10}\n")
            }

            button2.setOnClickListener {
                checkBox.isChecked = false
                checkBox2.isChecked = true
                (checkBox3 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_UNCHECKED
                (checkBox4 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_INDETERMINATE
                (checkBox5 as MaterialCheckBox).checkedState = MaterialCheckBox.STATE_UNCHECKED
            }

            // 리스너
            // 체크 여부가 변경 되었을 때
            checkBox.setOnCheckedChangeListener { view, isChecked ->
                textView.text = when(isChecked){
                    true -> "checkBox가 체크 되었습니다."
                    else -> "checkBox가 체크 해제 되었습니다."
                }
            }

            // 두 번째 : 체크 상태 값
            (checkBox4 as MaterialCheckBox).addOnCheckedStateChangedListener { checkBox, state ->
                textView.text = when(state){
                    MaterialCheckBox.STATE_CHECKED -> "checkBox4가 체크 되었습니다"
                    MaterialCheckBox.STATE_UNCHECKED -> "checkBox4가 체크 해제되었습니다"
                    MaterialCheckBox.STATE_INDETERMINATE -> "checkBox가 빼기 상태가 되었습니다"
                    else -> ""
                }
            }
        }
    }

    fun getCheckedString(checked:Boolean) = when(checked){
        true -> "체크 되어 있습니다"
        false -> "체크 되어 있지 않습니다"
    }

    fun getCheckedState(checkedState:Int) = when(checkedState){
        MaterialCheckBox.STATE_CHECKED -> "체크 되어 있습니다"
        MaterialCheckBox.STATE_UNCHECKED -> "체크 되어 있지 않습니다"
        MaterialCheckBox.STATE_INDETERMINATE -> "빼기 표시되어 있습니다"
        else -> ""
    }
}