package kr.co.lion.android11_material3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android11_material3.databinding.ActivityMainBinding

// Material : 구글에서 만들어 배포하고 있는 UI 라이브러리
// android, flutter, figma 등등에서 사용할 수 있도록 배포하고 있다.
// 현재는 3 버전이다.
// https://m3.material.io

// 최신 버전은 다음 페이지에서 확인한다.
// https://github.com/material-components/material-components-android/releases

// Material 3 라이브러리를 적용해주면 그냥 Button을 사용해도 MaterialButton 처럼 사용할 수 있다.
// 이는 MaterialButton과 Button이 크게 다르지 않고 다양한 테마를 제공하는 형태로 되어 있기 때문이다.
//[ MaterialButtonToggleGroup ]
//1. 주요 속성
//- singleSelection : true로 주면 하나만 선택할 수 있다.
//- selectionRequired : singleSelection에 true를 줬을 때만 사용하며 true로 주면 선택한 것을 취소할 수 없다.

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // Button과 MaterialButton은 서로 같은 것이라고 보면 된다.
            button.setOnClickListener {
                textView.text = "Material Button을 눌렀습니다"
            }
            button2.setOnClickListener {
                textView.text = "Basic Button을 눌렀습니다"
            }

            // MaterialButtonToggleGroup 의 버튼을 선택해준다.
            toggleGroup1.check(R.id.toggleButton1)
            toggleGroup1.check(R.id.toggleButton3)

            toggleGroup1.check(R.id.toggleButton2)
            // 선택 취소
            toggleGroup1.uncheck(R.id.toggleButton2)

            // SingleSelection에 true가 설정되어 있는 그룹에 버튼을 선택해준다.
            toggleGroup2.check(R.id.toggleButton4)
            toggleGroup2.check(R.id.toggleButton5)

            // 그룹 내부의 버튼의 체크 상태가 변경되었을 때
            // 두 번째 : 체크 상태가 변경된 버튼의 id
            // 세 번째 : 체크 되어는지 여부(true/false)
            toggleGroup1.addOnButtonCheckedListener{ group, checkedId, isChecked ->
                when(checkedId){
                    R.id.toggleButton1 -> {
                        if (isChecked) textView.text = "토글 버튼 1이 체크 되었습니다."
                        else textView.text = "토글 버튼 1이 체크 해제 되었습니다."
                    }

                    R.id.toggleButton2 -> {
                        if (isChecked) textView.text = "토글 버튼 2이 체크 되었습니다."
                        else textView.text = "토글 버튼 2이 체크 해제 되었습니다."
                    }

                    R.id.toggleButton3-> {
                        if (isChecked) textView.text = "토글 버튼 3이 체크 되었습니다."
                        else textView.text = "토글 버튼 3이 체크 해제 되었습니다."
                    }
                }
            }

            // SingleSelection
            toggleGroup2.addOnButtonCheckedListener { group, checkedId, isChecked ->
                // 버튼의 id로 분기한다.
                when(checkedId){
                    R.id.toggleButton4 -> {
                        if(isChecked){
                            textView.text = "토글 버튼 4가 체크 되었습니다."
                        }
                    }
                    R.id.toggleButton5-> {
                        if(isChecked){
                            textView.text = "토글 버튼 5가 체크 되었습니다."
                        }
                    }
                    R.id.toggleButton6 -> {
                        if(isChecked){
                            textView.text = "토글 버튼 6이 체크 되었습니다."
                        }
                    }
                }
            }

            buttonResult.setOnClickListener {
                textView.text = ""

                // toggle1에서 체크되어 있는 버튼들의 아이디를 모두 가져온다.
                // SingleSelection이 아니기 때문에 0 개 이상이 선택 되어 있을 수도 있다.
                toggleGroup1.checkedButtonIds.forEach {
                    when(it){
                        R.id.toggleButton1 -> textView.append("토글1이 체크 되어 있습니다\n")
                        R.id.toggleButton2 -> textView.append("토글2가 체크 되어 있습니다\n")
                        R.id.toggleButton3 -> textView.append("토글3이 체크 되어 있습니다\n")
                    }
                }

                // toggle2에서 체크되어 있는 버튼의 아이디를 가져온다.
                // SingleSelection이기 때문에 1개만 가져온다.
                when(toggleGroup2.checkedButtonId){
                    R.id.toggleButton4 -> textView.append("토글4가 체크 되어 있습니다\n")
                    R.id.toggleButton5 -> textView.append("토글5가 체크 되어 있습니다\n")
                    R.id.toggleButton6 -> textView.append("토글6이 체크 되어 있습니다\n")
                }
            }
        }
    }
}