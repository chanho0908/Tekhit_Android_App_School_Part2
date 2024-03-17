package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.myproject.techit_memo_proejct.MainActivity
import com.myproject.techit_memo_proejct.R
import com.myproject.techit_memo_proejct.databinding.FragmentJoinBinding
import kr.co.lion.androidproject4boardapp.MainFragmentName


class JoinFragment : Fragment() {

    private var _binding: FragmentJoinBinding ?= null
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonJoinNext()

        return binding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        binding.apply {
            toolbarJoin.apply {
                // 타이틀
                title = "회원가입"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 이전 화면으로 간다.
                    mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    // 다음 버튼
    fun settingButtonJoinNext(){
        binding.apply {
            buttonJoinNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // AddUserInfoFragment를 보여준다.
                    mainActivity.replaceFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
                }
            }
        }
    }
}








