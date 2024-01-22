package kr.co.lion.ex_06_material_chkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.ex_06_material_chkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkBoxList = ArrayList<MaterialCheckBox>()

        binding.apply {
            checkBoxList.add(checkBoxBasketball)
            checkBoxList.add(checkBoxSoccer)
            checkBoxList.add(checkBoxBaseball)

            hobbySwitch.setOnCheckedChangeListener{ v, isChecked ->
                when(isChecked){
                    true -> setCheckable()
                    false -> setUnCheckable()
                }
            }

            button.setOnClickListener {
                textView.text = ""
                checkBoxList.forEach { checkBox->
                    if (checkBox.isChecked){
                        textView.append("${checkBox.text} \n")
                    }
                }

                if(textView.text == ""){
                    textView.text = "취미가 없잖슴~"
                }
            }
        }
    }

    fun setCheckable(){
        binding.checkBoxBaseball.isEnabled = true
        binding.checkBoxSoccer.isEnabled = true
        binding.checkBoxBasketball.isEnabled = true
    }

    fun setUnCheckable(){


        binding.checkBoxBaseball.isEnabled = false
        binding.checkBoxBaseball.isChecked = false

        binding.checkBoxSoccer.isEnabled = false
        binding.checkBoxSoccer.isChecked = false

        binding.checkBoxBasketball.isEnabled = false
        binding.checkBoxBasketball.isChecked = false
    }
}