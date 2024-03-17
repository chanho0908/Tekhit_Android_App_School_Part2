package com.myproject.my_board_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.my_board_project.ContentActivity
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentModifyContentBinding
import com.myproject.my_board_project.databinding.FragmentModifyUserBinding
import kr.co.lion.androidproject4boardapp.ContentFragmentName

class ModifyContentFragment : Fragment() {
    private var _binding: FragmentModifyContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var contentActivity: ContentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyContentBinding.inflate(layoutInflater)
        contentActivity = activity as ContentActivity

        settingToolbarModifyContent()

        return binding.root
    }

    // 툴바 설정
    private fun settingToolbarModifyContent(){
        with(binding) {
            toolbarModifyContent.apply {
                // 타이틀
                title = "글 수정"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    contentActivity.removeFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT)
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_content)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}