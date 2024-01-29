package kr.co.lion.ex09_activity_example.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex09_activity_example.R
import kr.co.lion.ex09_activity_example.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            materialToolbar.apply {
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    val studentName = studentName.text.toString()
                    val studentGrade = studentGrade.text.toString()
                    val studentKor = kor.text.toString()
                    val studentEng = eng.text.toString()
                    val studentMath = math.text.toString()

                    val intent = Intent()
                    intent.putExtra("studentName", studentName)
                    intent.putExtra("studentGrade", studentGrade)
                    intent.putExtra("studentKor", studentKor)
                    intent.putExtra("studentEng", studentEng)
                    intent.putExtra("studentMath", studentMath)

                    setResult(RESULT_OK, intent)

                    finish()
                }
            }
        }
    }
}