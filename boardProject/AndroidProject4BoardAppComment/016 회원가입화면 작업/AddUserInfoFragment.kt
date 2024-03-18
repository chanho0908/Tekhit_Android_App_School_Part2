package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentAddUserInfoBinding
import kr.co.lion.androidproject4boardapp.viewmodel.AddUserInfoViewModel

class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity
    lateinit var addUserInfoViewModel: AddUserInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        fragmentAddUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user_info, container, false)
        addUserInfoViewModel = AddUserInfoViewModel()
        fragmentAddUserInfoBinding.addUserInfoViewModel = addUserInfoViewModel
        fragmentAddUserInfoBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonAddUserInfoSubmit()
        settingInputUI()
        settingCheckBox()

        return fragmentAddUserInfoBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        fragmentAddUserInfoBinding.apply {
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
        fragmentAddUserInfoBinding.apply {
            buttonAddUserInfoSubmit.apply {
                // 눌렀을 때
                setOnClickListener {
                     mainActivity.removeFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT)
                     mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    // 입력 요소 관련 설정
    fun settingInputUI(){
        // 입력 요소들을 초기화 한다.
        addUserInfoViewModel.textFieldAddUserInfoNickName.value = ""
        addUserInfoViewModel.textFieldAddUserInfoAge.value = ""
        addUserInfoViewModel.settingGender(Gender.MALE)

        // 닉네임에 포커스를 준다.
        Tools.showSoftInput(mainActivity, fragmentAddUserInfoBinding.textFieldAddUserInfoNickName)
    }

    // 체크박스 관련 설정
    fun settingCheckBox(){
        // 모든 체크박스를 초기화한다.
        addUserInfoViewModel.checkBoxAddUserInfoHobby1.value = false
        addUserInfoViewModel.checkBoxAddUserInfoHobby2.value = false
        addUserInfoViewModel.checkBoxAddUserInfoHobby3.value = true
        addUserInfoViewModel.checkBoxAddUserInfoHobby4.value = false
        addUserInfoViewModel.checkBoxAddUserInfoHobby5.value = true
        addUserInfoViewModel.checkBoxAddUserInfoHobby6.value = false
    }
}







