package kr.co.lion.ex09_activity_example.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.ex09_activity_example.R
import kr.co.lion.ex09_activity_example.databinding.ActivityFourthBinding
import kr.co.lion.ex09_activity_example.databinding.ActivityThirdBinding

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("storeName").toString()
        val grade = intent.getStringExtra("storeGrade").toString()
        val kor = intent!!.getIntExtra("storeKor", 0)
        val eng = intent!!.getIntExtra("storeEng", 0)
        val math = intent!!.getIntExtra("storeMath", 0)

        val sum = kor + eng + math
        val avg = sum / 3

        binding.apply {
            studentName.text = name
            studentGrade.text = grade
            scoreKor.text = kor.toString()
            scoreEng.text = eng.toString()
            scoreMath.text = math.toString()
            scoreSum.text = sum.toString()
            scoreAvg.text = avg.toString()

            materialToolbar.apply {
                setNavigationIcon(R.drawable.arrow_back_24px)

                setNavigationOnClickListener {

                    finish()
                }
            }

        }
    }
}