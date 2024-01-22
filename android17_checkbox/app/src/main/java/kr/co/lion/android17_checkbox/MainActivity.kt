package kr.co.lion.android17_checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.android17_checkbox.databinding.ActivityMainBinding

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
                textView.text = "check2 checked : ${a3}\n"
                textView.append("check2 state : ${a4}\n")

                val a5 = getCheckedString(checkBox3.isChecked)
                val a6 = getCheckedState((checkBox3 as MaterialCheckBox).checkedState)
                textView.text = "check3 checked : ${a5}\n"
                textView.append("check3 state : ${a6}\n")

                
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