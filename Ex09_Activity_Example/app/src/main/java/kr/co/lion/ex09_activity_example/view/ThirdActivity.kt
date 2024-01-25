package kr.co.lion.ex09_activity_example.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex09_activity_example.R
import kr.co.lion.ex09_activity_example.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calcList = intent.getIntegerArrayListExtra("calcResult")

        binding.apply {
            textView.text = ""

            textView.append("국어 총점 : ${calcList?.get(0)} \n")
            textView.append("국어 평균 : ${calcList?.get(1)} \n")

            textView.append("영어 총점 : ${calcList?.get(2)} \n")
            textView.append("영여 평균 : ${calcList?.get(3)} \n")

            textView.append("수학 총점 : ${calcList?.get(4)} \n")
            textView.append("수학 평균 : ${calcList?.get(5)} \n\n")

            textView.append("전체 총점 : ${calcList?.get(6)} \n")
            textView.append("전체 평균 : ${calcList?.get(7)} ")

            button.setOnClickListener { finish() }
        }

    }
}