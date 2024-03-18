package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyContentBinding


class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyContentBinding: FragmentModifyContentBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentModifyContentBinding = FragmentModifyContentBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingToolbarModifyUser()

        return fragmentModifyContentBinding.root
    }

    // 툴바 설정
    fun settingToolbarModifyUser(){
        fragmentModifyContentBinding.apply {
            toolbarModifyContent.apply {
                // 타이틀
                title = "회원 정보 수정"
                // 메뉴
                inflateMenu(R.menu.menu_modify_user)
            }
        }
    }
}