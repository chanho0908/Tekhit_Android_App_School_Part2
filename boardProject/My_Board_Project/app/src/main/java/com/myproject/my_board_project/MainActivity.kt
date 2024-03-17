package com.myproject.my_board_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.myproject.my_board_project.databinding.ActivityMainBinding
import com.myproject.my_board_project.fragment.AddUserInfoFragment
import com.myproject.my_board_project.fragment.JoinFragment
import com.myproject.my_board_project.fragment.LoginFragment
import com.myproject.my_board_project.viewModel.AddUserInfoViewModel
import kr.co.lion.androidproject4boardapp.MainFragmentName

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 프래그먼트의 주소값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("sdasdsadas", "onCraete")
        // 첫 화면을 띄워준다.
        replaceFragment(MainFragmentName.LOGIN_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name: MainFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?){
        SystemClock.sleep(200)

        val transaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        when(name){
            MainFragmentName.LOGIN_FRAGMENT ->{
                newFragment = LoginFragment()
            }
            // 회원가입 화면 1
            MainFragmentName.JOIN_FRAGMENT -> {
                newFragment = JoinFragment()
            }
            // 회원가입 화면 2
            MainFragmentName.ADD_USER_INFO_FRAGMENT -> {
                newFragment = AddUserInfoFragment()
            }
        }

        if(data != null){
            newFragment?.arguments = data
        }

        if(isAnimate) {
            // oldFragment -> newFragment
            // oldFragment : exitTransition
            // newFragment : enterTransition

            // newFragment -> oldFragment
            // oldFragment : reenterTransition
            // newFragment : returnTransition

            // MaterialSharedAxis : 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
            // X - 좌우
            // Y - 위아래
            // Z - 공중 바닥
            // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지 여부를 설정해준다.
            // true : 새로운 화면이 나타나는 애니메이션이 동작한다.
            // false : 이전으로 되돌아가는 애니메이션이 동작한다.

            if(oldFragment != null){
                // old에서 -> new가 새롭게 보여질 때 old의 애니메이션
                oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈때 old의 애니메이션
                oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                oldFragment?.enterTransition = null
                oldFragment?.returnTransition = null

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }


            transaction.replace(R.id.containerMain, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if(addToBackStack){
                // BackStack 포함 시킬때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                transaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            transaction.commit()

        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name:MainFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}