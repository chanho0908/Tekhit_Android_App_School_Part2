package kr.co.lion.android21_materialcardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// CardView
// 화면에 배치되는 뷰들을 카드라는 것으로 묶어서 표현할 수 있다.
// style
// Outlined : 기본
// Filled : 내부를 색상으로 채움
// Elevated 
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}