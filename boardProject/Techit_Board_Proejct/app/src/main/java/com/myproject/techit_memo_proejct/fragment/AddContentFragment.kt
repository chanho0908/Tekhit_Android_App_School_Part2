package com.myproject.techit_memo_proejct.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.techit_memo_proejct.R
import com.myproject.techit_memo_proejct.databinding.FragmentAddContentBinding
import kr.co.lion.androidproject4boardapp.ContentFragmentName


class AddContentFragment : Fragment() {

    lateinit var fragmentAddContentBinding: FragmentAddContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentAddContentBinding = FragmentAddContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbarAddContent()

        return fragmentAddContentBinding.root
    }

    // 툴바 셋팅
    fun settingToolbarAddContent(){
        fragmentAddContentBinding.apply {
            toolbarAddContent.apply {
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
                    when(it.itemId){
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

                            contentActivity.replaceFragment(ContentFragmentName.READ_CONTENT_FRAGMENT, true, true, null)
                        }
                    }
                    true
                }
            }
        }
    }
}