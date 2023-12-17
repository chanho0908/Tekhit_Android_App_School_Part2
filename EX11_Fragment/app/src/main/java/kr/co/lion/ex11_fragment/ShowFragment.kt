package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex11_fragment.databinding.FragmentShowBinding

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
        // position 값을 가져온다.
        val position = arguments?.getInt("position")!!
        // position번째 객체를 가져온다.
        val studentInfo = mainActivity.studentInfoList[position]
        // 출력
        fragmentShowBinding.apply {
            textViewShow.apply {
                text = "이름 : ${studentInfo.name}\n"
                append("나이 : ${studentInfo.age}\n")
                append("국어 점수 : ${studentInfo.kor}")
            }
        }
    }
}








