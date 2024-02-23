package com.myproject.android47_orientation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.myproject.android47_orientation.databinding.ActivityMainBinding

// 화면 회전 사건이 발생했을 때
// 1. 화면 회전 처리가 되기 전에 onSaveInstanceState 가 호출된다.
//    OS가 이 메서드를 호출할 때 Bundle객체를 전달해준다.
//    화면 복원에 필요한 데이터를 Bundle객체에 담아준다.
// 2. 화면 회전이 발생한다.
// 3. onCreate가 호출된다. 이 때, 1에서 데이터를 담은 Bundle객체가 매개변수로 들어온다.
//    Bundle객체 담긴 데이터를 추출하여 View에 넣어줘서 복원작업을 수행한다.

// 가로 모드 대응시 주의할 점.
// 1. 세로모드에서 사용하는 xml과 가로모드에서 사용하는 xml 파일의 이름이 같아야한다
// 2. 세로모드의 UI 요소들과 가로모드의 UI 요소들의 아이디가 모두 일치해야 한다.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if(savedInstanceState == null){
            Log.d("test1234", "Activity 실행")
        } else {
            Log.d("test1234", "화면 회전 발생")
        }

        with(binding){
            button.setOnClickListener {
                textView.text = "안농~"
            }

            button2.setOnClickListener {
                textView.text = editTextText.text
            }
        }
    }
}