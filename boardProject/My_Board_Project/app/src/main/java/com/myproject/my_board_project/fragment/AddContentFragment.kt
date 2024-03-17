package com.myproject.my_board_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.my_board_project.ContentActivity
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentAddContentBinding
import kr.co.lion.androidproject4boardapp.ContentFragmentName

class AddContentFragment : Fragment() {
    private var _binding: FragmentAddContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var contentActivity: ContentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddContentBinding.inflate(layoutInflater)
        contentActivity = activity as ContentActivity

        settingToolbarAddContent()

        return binding.root
    }

    // 툴바 셋팅
    private fun settingToolbarAddContent() {
        with(binding.toolbarAddContent) {

            // 타이틀
            title = "글 작성"
            // Back
            setNavigationIcon(R.drawable.arrow_back_24px)
            setNavigationOnClickListener {
                contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
            }
            // 메뉴
            inflateMenu(R.menu.menu_add_content)
            setOnMenuItemClickListener {
                // 메뉴의 id로 분기한다.
                when (it.itemId) {
                    // 카메라
                    R.id.menuItemAddContentCamera -> {

                    }
                    // 앨범
                    R.id.menuItemModifyContentAlbum -> {

                    }
                    // 초기화
                    R.id.menuItemModifyContentReset -> {

                    }
                    // 완료
                    R.id.menuItemModifyContentDone -> {
                        // ReadContentFragment로 이동한다.

                        contentActivity.replaceFragment(
                            ContentFragmentName.READ_CONTENT_FRAGMENT,
                            true,
                            true,
                            null
                        )
                    }
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}