package com.myproject.my_board_project.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.myproject.my_board_project.MainActivity
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentAddUserInfoBinding
import com.myproject.my_board_project.viewModel.AddUserInfoViewModel
import kr.co.lion.androidproject4boardapp.MainFragmentName

class AddUserInfoFragment : Fragment() {
    private var _binding: FragmentAddUserInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private val addUserInfoViewModel: AddUserInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddUserInfoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        return binding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        with(binding) {
            toolbarAddUserInfo.apply {
                // 타이틀
                title = "회원가입"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT)
                }
            }
        }
    }

    // 가입 버튼
    fun settingButtonAddUserInfoSubmit(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}