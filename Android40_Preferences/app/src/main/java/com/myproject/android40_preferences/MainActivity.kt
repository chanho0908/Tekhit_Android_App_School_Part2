package com.myproject.android40_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.android40_preferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            button.setOnClickListener {
                // 첫 번째 : Preferences의 이름. 없으면 생성해준다.
                // 두 번째 : MODE_APPEND - 데이터를 저장할 때 사용한 이름의 데이터가 있어도
                //                        추가적으로 저장한다.
                //          MODE_PRIVATE - 데이터를 저장할 때 사용한 이름의 데이터가 있으면
                //                         덮어씌워준다.
                // 저장할 수 있는 데이터의 수는 무제한
                // 단, 저장 가능한 타입은 6가지 String, 원시타입 등...
                val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
                // 데이터를 저장하기 위한 객체를 추출한다.
                val editor = pref.edit()

                // editor 객체에 값을 담는다.
                editor.putBoolean("data1", true)
                editor.putFloat("data2", 11.11f)
                editor.putInt("data3", 100)
                editor.putLong("data4", 200L)
                editor.putString("data5", "문자열 데이터")

                val set1 = mutableSetOf<String>()
                set1.add("문자열1")
                set1.add("문자열2")
                set1.add("문자열3")
                editor.putStringSet("data5", set1)
            }

            button2.setOnClickListener {
                val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
                // 저장한 데이터를 가져온다.
                // 두 번째 매개변수에는 기본값을 설정할 수 있다.
                // 지정된 이름으로 저장된 데이터가 없을 경우에는 두 번째 매개변수로
                // 지정한 기본값이 반환되어 변수에 담긴다.
                val data1 = pref.getBoolean("data1", false)
                val data2 = pref.getFloat("data2", 0.0f)
                val data3 = pref.getInt("data3", 0)
                val data4 = pref.getLong("data4", 0L)
                val data5 = pref.getString("data5", null)
                val data6 = pref.getStringSet("data6", null)

                textView.apply {
                    text = "data1 : ${data1}\n"
                    append("data2 : ${data2}\n")
                    append("data3 : ${data3}\n")
                    append("data4 : ${data4}\n")

                    if(data5 != null){
                        append("data5 : ${data5}\n")
                    }

                    if(data6 != null){
                        data6.forEach {
                            append("data6 : ${it}\n")
                        }
                    }
                }
            }


        }
    }
}