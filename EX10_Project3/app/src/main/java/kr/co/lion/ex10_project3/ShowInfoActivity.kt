package kr.co.lion.ex10_project3

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10_project3.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setView()
    }

    fun setToolbar(){
        binding.apply {
            toolbarShowInfo.apply {
                // 타이틀
                title = "학생 정보 보기"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }

    fun setView(){
        binding.apply {
            // TextView
            textViewShowInfoReport.apply {

                // Intent로 부터 학생 정보 객체를 추출한다.
                val studentData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("studentData", StudentData::class.java)
                } else {
                    intent.getParcelableExtra<StudentData>("studentData")
                }

                text = "이름 : ${studentData?.name}\n"
                append("학년 : ${studentData?.grade}학년\n")
                append("\n")
                append("국어 점수 : ${studentData?.kor}점\n")
                append("영어 점수 : ${studentData?.eng}점\n")
                append("수학 점수 : ${studentData?.math}점\n")
                append("\n")

                val total = studentData!!.kor + studentData!!.math + studentData!!.eng
                append("총점 : ${total}점\n")
                append("평균 : ${total / 3}점")
            }
        }
    }
}