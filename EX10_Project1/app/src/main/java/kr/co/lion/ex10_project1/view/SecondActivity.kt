package kr.co.lion.ex10_project1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.co.lion.ex10_project1.R
import kr.co.lion.ex10_project1.databinding.ActivityMainBinding
import kr.co.lion.ex10_project1.databinding.ActivitySecondBinding
import kr.co.lion.ex10_project1.model.Student

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            toolbar.apply {

            inflateMenu(R.menu.second_menu)

            setNavigationOnClickListener { finish() }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.done -> {

                        val name = studentName.text.toString()
                        val grade = Integer.parseInt(studentGrade.text.toString())
                        val kor = Integer.parseInt(kor.text.toString())
                        val eng = Integer.parseInt(eng.text.toString())
                        val math = Integer.parseInt(math.text.toString())

                        val student = Student(name, grade, kor, eng, math)
                        val intent = Intent()
                        intent.putExtra("student", student)

                        setResult(RESULT_OK, intent)

                        finish()

                        true
                    }

                    else -> true
                }
            }
        }
        }
    }

}