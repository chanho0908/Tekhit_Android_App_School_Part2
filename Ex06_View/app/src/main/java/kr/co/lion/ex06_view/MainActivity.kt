package kr.co.lion.ex06_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.ex06_view.databinding.ActivityMainBinding

// 1. 화면 부터 만들어주세요. (화면이 다수라면 화면 전환까지)
// 2. 각 화면별 화면에 대한 기능을 모두 구현해주세요.
// 3. 각 화면별 구현해야 하는 기능을 정의해주세요.
// 4. 각 화면별 구현해야 하는 기능을 구현해주세요.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setViewEvent()
    }



    // 화면 요소에 관련된 초기화
    fun initView(){
        binding.apply {
            switchHobby.isChecked = true
        }
    }

    // 화면 요소에 대한 이벤트 설정
    fun setViewEvent(){
        // 취미 스위치 이벤트
        binding.apply {
            switchHobby.setOnCheckedChangeListener { v, isChecked ->
                when (isChecked) {
                    true -> {
                        // 보이게
//                        checkBoxHobby1.isVisible = true
//                        checkBoxHobby2.isVisible = true
//                        checkBoxHobby3.isVisible = true
                        // 활성화
                        checkBoxHobby1.isEnabled = true
                        checkBoxHobby2.isEnabled = true
                        checkBoxHobby3.isEnabled = true
                    }
                    // off 상태
                    false -> {
                        // 안보이게
//                        checkBoxHobby1.isVisible = false
//                        checkBoxHobby2.isVisible = false
//                        checkBoxHobby3.isVisible = false
                        // 비활성화
                        checkBoxHobby1.isEnabled = false
                        checkBoxHobby2.isEnabled = false
                        checkBoxHobby3.isEnabled = false
                    }
                }

                buttonSubmit.setOnClickListener {
                    textViewResult.text = "아이디 : ${textFieldUserId.text} \n"
                    textViewResult.append("비밀번호 : ${textFieldUserPw.text}\n")
                    textViewResult.append("이름 : ${textFieldUserName.text}\n")

                    when(switchHobby.isChecked){
                        // off 상태면 취미가 없는 것으로 취급한다.
                        false -> textViewResult.append("선택한 취미는 없습니다")
                        // on 상태면 체크박스에 체크한 것을 출력해준다.
                        true -> {
                            // 모든 체크박스가 체크되어 있지 않다면
                            if(!checkBoxHobby1.isChecked && !checkBoxHobby2.isChecked && !checkBoxHobby3.isChecked){
                                textViewResult.append("선택한 취미는 없습니다")
                            } else {
                                if(checkBoxHobby1.isChecked){
                                    textViewResult.append("선택한 취미 : 축구\n")
                                }
                                if(checkBoxHobby2.isChecked){
                                    textViewResult.append("선택한 취미 : 농구\n")
                                }
                                if(checkBoxHobby3.isChecked){
                                    textViewResult.append("선택한 취미 : 야구\n")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}