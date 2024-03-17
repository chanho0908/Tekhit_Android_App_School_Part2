package com.myproject.my_board_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentModifyUserBinding

class ModifyUserFragment : Fragment() {
    private var _binding: FragmentModifyUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyUserBinding.inflate(layoutInflater)
        settingToolbarModifyUser()
        return binding.root
    }

    // 툴바 설정
    private fun settingToolbarModifyUser(){
        with(binding) {
            toolbarModifyUser.apply {
                // 타이틀
                title = "회원 정보 수정"
                // 메뉴
                inflateMenu(R.menu.menu_modify_user)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}