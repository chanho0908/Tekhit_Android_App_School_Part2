package com.myproject.my_board_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.myproject.my_board_project.ContentActivity
import com.myproject.my_board_project.R
import com.myproject.my_board_project.databinding.FragmentMainBinding
import com.myproject.my_board_project.databinding.FragmentReadContentBinding
import kr.co.lion.androidproject4boardapp.ContentFragmentName

class ReadContentFragment : Fragment() {
    private var _binding: FragmentReadContentBinding? = null
    private val binding: FragmentReadContentBinding get() = _binding!!
    private lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentReadContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbarReadContent()
        settingBackButton()

        return binding.root
    }

    // 툴바 설정
    fun settingToolbarReadContent(){
        binding.apply {
            toolbarReadContent.apply {
                // 타이틀
                title = "글 읽기"
                // 네비게이션
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    backProcesss()
                }
                // 메뉴
                inflateMenu(R.menu.menu_read_content)
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        // 댓글
                        R.id.menuItemReadContentReply -> {
                            // 댓글을 보여줄 BottomSheet를 띄운다.
                            showReplyBottomSheet()
                        }
                        // 수정하기
                        R.id.menuItemReadContentModify -> {
                            // 수정 화면이 보이게 한다.
                            contentActivity.replaceFragment(ContentFragmentName.MODIFY_CONTENT_FRAGMENT, true, true, null)
                        }
                        // 삭제하기
                        R.id.menuItemReadContentDelete -> {

                        }
                    }
                    true
                }
            }
        }
    }

    // Back button 눌렀을 때
    fun settingBackButton(){
        contentActivity.onBackPressedDispatcher.addCallback {
            // 뒤로가기 처리 메서드 호출
            backProcesss()
            // 뒤로가기 버튼의 콜백을 제거한다.
            remove()
        }
    }

    // 뒤로가기 처리
    fun backProcesss(){
        contentActivity.removeFragment(ContentFragmentName.READ_CONTENT_FRAGMENT)
        contentActivity.removeFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT)
    }

    // 댓글을 보여불 BottomSheet를 띄워준다.
    fun showReplyBottomSheet(){
        val readContentBottomFragment = ReadContentBottomFragment()
        readContentBottomFragment.show(contentActivity.supportFragmentManager, "ReplyBottomSheet")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}