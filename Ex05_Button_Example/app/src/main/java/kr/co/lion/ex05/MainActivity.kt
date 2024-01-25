package kr.co.lion.ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.co.lion.ex05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnPlus.setOnClickListener(ButtonClickListener())
            btnSub.setOnClickListener(ButtonClickListener())
            btnMul.setOnClickListener(ButtonClickListener())
            btnDiv.setOnClickListener(ButtonClickListener())
        }
    }

    inner class ButtonClickListener : OnClickListener{
        override fun onClick(v: View?) {
            val num1: Int = Integer.parseInt(binding.num1.text.toString())
            val num2 =  Integer.parseInt(binding.num2.text.toString())

            when(v?.id){
                R.id.btn_plus -> setCalcResult(num1 + num2)
                R.id.btn_sub -> setCalcResult(num1 - num2)
                R.id.btn_mul -> setCalcResult(num1 * num2)
                R.id.btn_div -> setCalcResult(num1 / num2)
            }
        }
    }

    fun setCalcResult(result: Int) {
        binding.resultTv.text = "총합 : $result"
    }
}