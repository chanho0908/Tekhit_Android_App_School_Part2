package kr.co.lion.android06_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//[ConstraintLayout ]
//1. ConstraintLayout에 배치되는 View의 주요 속성
//- 부모 혹은 다른 뷰와의 관계를 설정한 제약 조건 속성들

// ConstraintLayout
// ConstraintLayout에 배치되는 뷰들은 부모 혹은 다른 뷰들과의 관계를 나타내는
// 제약 조건을 설정하여 뷰들을 배치한다.

// 제약 조건은 선으로 표시된다
// 직선 : 절대적 길이. 물리적인 애정 사이즈에 관계 없이 고정된 길이. 길이
// 지그 재그선 : 상대적 길이. 물리적인 액정 사이즈에 따라 변화되는 길이. 비율
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}