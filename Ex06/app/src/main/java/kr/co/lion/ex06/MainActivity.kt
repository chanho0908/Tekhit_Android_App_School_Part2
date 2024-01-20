package kr.co.lion.ex06

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.co.lion.ex06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            button.setOnClickListener {
                val name = name.text.toString()
                val age = age.text.toString()
                val kor = Integer.parseInt(kor.text.toString())
                val math = Integer.parseInt(math.text.toString())
                val avg = (kor+math) / 2
                textView.text = "이름 : $name \n" + "나이 : $age \n " + "국어 : $kor \n" + "수학 : $math \n" + "평균 : $avg"


            }
        }
    }


    inner class onClickHandlingListener : OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){

            }
        }

    }
}