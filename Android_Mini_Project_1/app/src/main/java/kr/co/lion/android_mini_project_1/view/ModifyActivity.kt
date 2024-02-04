package kr.co.lion.android_mini_project_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android_mini_project_1.R
import kr.co.lion.android_mini_project_1.databinding.ActivityModifyBinding


class ModifyActivity : AppCompatActivity() {

    lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
    }

    // 툴바 설정
    fun setToolbar(){
        binding.apply {
            toolbarModify.apply {
                // 타이틀
                title = "동물 정보 수정"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_modify)
            }
        }
    }
}