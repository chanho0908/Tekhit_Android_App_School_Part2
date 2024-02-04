package kr.co.lion.android_mini_project_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android_mini_project_1.R
import kr.co.lion.android_mini_project_1.databinding.ActivityInputBinding


class InputActivity : AppCompatActivity() {

    lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setEvent()
        setToolbar()
    }

    // 툴바 설정
    fun setToolbar(){
        binding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "동물 등록"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_input)
            }
        }
    }

    // View 들의 초기 상태를 설정한다.
    fun initView(){
        binding.apply {
            // 버튼 그룹에서 사자를 선택한다.
            buttonGroupInputType.check(R.id.buttonInputType1);
            // 입력요소를 관리하는 layout 들 중 사자는 보이게 하고
            // 나머지들은 보이지 않게 한다.
            containerInputType1.isVisible = true
            containerInputType2.isVisible = false
            containerInputType3.isVisible = false

            buttonGroupInputGender.check(R.id.buttonInputGender1)
        }
    }

    // View들의 이벤트 설정
    fun setEvent(){
        binding.apply {
            // 동물 타입 버튼 크룹
            buttonGroupInputType.addOnButtonCheckedListener { group, checkedId, isChecked ->

                // 전부다 안보이는 상태로 변경한다.
                containerInputType1.isVisible = false
                containerInputType2.isVisible = false
                containerInputType3.isVisible = false

                // 현재 체크되어 있는 버튼에 따라 보여지는 부분을 달리한다.
                when(buttonGroupInputType.checkedButtonId){
                    // 사자
                    R.id.buttonInputType1 -> {
                        containerInputType1.isVisible = true
                    }
                    // 호랑이
                    R.id.buttonInputType2 -> {
                        containerInputType2.isVisible = true
                    }
                    // 기린
                    R.id.buttonInputType3 -> {
                        containerInputType3.isVisible = true
                    }
                }
            }
        }
    }
}