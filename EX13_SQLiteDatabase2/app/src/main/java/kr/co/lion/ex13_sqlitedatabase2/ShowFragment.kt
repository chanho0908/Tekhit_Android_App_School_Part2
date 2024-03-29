package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingView()

        return fragmentShowBinding.root
    }

    // 툴바 셋팅
    fun settingToolbar(){
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "정보 보기"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }
            }
        }
    }

    // View 설정
    fun settingView(){

        // 출력
        fragmentShowBinding.apply {
            textViewShow.apply {
                text = "이름 : 홍길동\n"
                append("나이 : 30살\n")
                append("국어 점수 : 100점")
            }
        }
    }
}








