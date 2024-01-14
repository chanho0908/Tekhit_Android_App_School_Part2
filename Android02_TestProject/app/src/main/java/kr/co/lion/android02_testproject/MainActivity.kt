package kr.co.lion.android02_testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// step1) 화면 부터 구성해준다.
// onCreate 메서드에 있는 코드 중에 setContentView 메서드의 매개변수에
// 설정되어 있는 것을 확인하여 res/layout 폴더에 있는 xml 파일을 열어준다.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}