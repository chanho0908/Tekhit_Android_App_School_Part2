package kr.co.lion.androidproject4boardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kr.co.lion.androidproject4boardapp.databinding.ActivityContentBinding
import kr.co.lion.androidproject4boardapp.databinding.HeaderContentDrawerBinding

class ContentActivity : AppCompatActivity() {

    lateinit var activityContentBinding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityContentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(activityContentBinding.root)

        settingNavigationView()
    }
    
    // 네비게이션 뷰 설정
    fun settingNavigationView(){
        activityContentBinding.apply { 
            navigationViewContent.apply { 
                // 헤더로 보여줄 View를 생성한다.
                val headerContentDrawerBinding = HeaderContentDrawerBinding.inflate(layoutInflater)
                // 헤더로 보여줄 View를 설정한다.
                addHeaderView(headerContentDrawerBinding.root)
                
                // 사용자 닉네임을 설정한다.
                headerContentDrawerBinding.headerContentDrawerNickName.text = "홍길동님"

                // 메뉴를 눌렀을 때 동작하는 리스너
                setNavigationItemSelectedListener {

                    // 딜레이
                    SystemClock.sleep(200)

                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 전체 게시판
                        R.id.menuItemContentNavigationAll -> {
                            // NavigationView를 닫아준다.
                            drawerLayoutContent.close()
                        }
                        // 자유 게시판
                        R.id.menuItemContentNavigation1 -> {
                            // NavigationView를 닫아준다.
                            drawerLayoutContent.close()
                        }
                    }

                    true
                }
            }
        }
    }
}