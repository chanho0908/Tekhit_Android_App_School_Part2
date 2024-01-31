package kr.co.lion.ex10_project1.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10_project1.R
import kr.co.lion.ex10_project1.databinding.ActivityThirdBinding
import kr.co.lion.ex10_project1.model.Student

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent로 부터 객체를 추출한다
        val info = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra("select", Student::class.java)
        } else {
            intent?.getParcelableExtra<Student>("select")
        }

        binding.apply {

            val name = info?.name
            val grade = info?.grade
            val kor = info?.kor
            val eng = info?.eng
            val math = info?.math

            val sum = kor!! + eng!! + math!!

            studentName.text = name
            studentGrade.text = grade.toString()
            scoreKor.text = kor.toString()
            scoreEng.text = eng.toString()
            scoreMath.text = math.toString()
            scoreSum.text = sum.toString()
            scoreAvg.text = (sum / 3).toString()

            materialToolbar.apply {
                setNavigationOnClickListener {

                    finish()
                }
            }

        }
    }
}