package kr.co.lion.android26_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android26_startactivity.databinding.ActivityFourthBinding


class FourthActivity : AppCompatActivity() {

    lateinit var activityFourthBinding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(activityFourthBinding.root)

        activityFourthBinding.apply {
            textViewFourth.apply {
                // intent에 담겨서 전달된 데이터를 가져온다
                val data1 = intent?.getIntExtra("data1", 0)
                val data2 = intent?.getDoubleExtra("data2", 0.0)
                val data3 = intent?.getBooleanExtra("data3", false)

                text = "SecondActivity\n"
                append("data1 : ${data1}\n")
                append("data2 : ${data2}\n")
                append("data3 : $data3")
            }

            buttonFinishFourth.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("value1", 300)
                resultIntent.putExtra("value2", 33.33)
                resultIntent.putExtra("value3", true)

                // 작업의 결과를 설정한때 Intent도 설정해주면
                // 이전 Activity로 전달된다.
                setResult(RESULT_OK, resultIntent)

                finish()
            }
        }

    }
}