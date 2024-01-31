package kr.co.lion.android29_message

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReqeustActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reqeust)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RequestFragment())
            .commit()
    }

    // 데이터를 전달하는 Intent 생성
    fun createIntentWithData(data: String): Intent {
        val intent = Intent(this, TestActivity::class.java)
        intent.putExtra("KEY_DATA", data)
        return intent
    }
}