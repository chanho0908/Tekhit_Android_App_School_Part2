package com.myproject.my_board_project.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.my_board_project.ContentActivity
import com.myproject.my_board_project.MainActivity
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentLoginBinding
import kr.co.lion.androidproject4boardapp.MainFragmentName

class LoginFragment : Fragment() {
//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
    lateinit var binding: FragmentLoginBinding
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        Log.d("sdasdsadas", "onCreateView")
        settingToolbar()
        settingButtonLoginJoin()
        settingButtonLoginSubmit()

        return binding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        with(binding) {
            toolbarLogin.apply {
                // 타이틀
                title = "로그인"
            }
        }
    }

    // 회원 가입 버튼
    private fun settingButtonLoginJoin(){
        with(binding) {
            buttonLoginJoin.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // JoinFragment가 보여지게 한다.
                    mainActivity.replaceFragment(MainFragmentName.JOIN_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 로그인 버튼
    private fun settingButtonLoginSubmit(){
        with(binding) {
            buttonLoginSubmit.apply {
                // 버튼을 눌렀을 때
                setOnClickListener {
                    // ContentActivity를 실행한다.
                    val contentIntent = Intent(mainActivity, ContentActivity::class.java)
                    startActivity(contentIntent)
                    // MainActivity를 종료한다.
                    mainActivity.finish()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}