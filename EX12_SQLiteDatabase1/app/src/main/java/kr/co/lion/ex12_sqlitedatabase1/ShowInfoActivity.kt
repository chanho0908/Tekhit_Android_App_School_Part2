package kr.co.lion.ex12_sqlitedatabase1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex12_sqlitedatabase1.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {

    lateinit var activityShowInfoBinding: ActivityShowInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowInfoBinding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(activityShowInfoBinding.root)

        setToolbar()
        setView()
    }

    // 툴바 설정
    fun setToolbar(){
        activityShowInfoBinding.apply {
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

    // 뷰 설정
    fun setView(){
        activityShowInfoBinding.apply {
            // TextView
            textViewShowInfoReport.apply {

                text = "이름 : 홍길동\n"
                append("학년 : 1학년\n")
                append("\n")
                append("국어 점수 : 100점\n")
                append("영어 점수 : 100점\n")
                append("수학 점수 : 100점\n")
                append("\n")

                append("총점 : 300점\n")
                append("평균 : 100점")
            }
        }
    }
}