package kr.co.lion.android27_toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android27_toolbar.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            materialToolbar.apply {
                title = "SecondActivity"

                // 좌측 상단의홈 버튼 아이콘 설정
                // 좌측 상단의 홈 버튼 아이콘을 설정한다.
                setNavigationIcon(R.drawable.arrow_back_24px)
                // 좌측 상단의 홈 버튼 아이콘을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    // 현재 Activity를 종료시킨다.
                    finish()
                }
            }


        }
    }
}