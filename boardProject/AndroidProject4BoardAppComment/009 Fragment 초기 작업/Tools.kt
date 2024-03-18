package kr.co.lion.androidproject4boardapp

class Tools {

    companion object{

    }
}

// MainActivity에서 보여줄 프래그먼트들의 이름
enum class MainFragmentName(var str:String){
    LOGIN_FRAGMENT("LoginFragment"),
    JOIN_FRAGMENT("JoinFragment"),
    ADD_USER_INFO_FRAGMENT("AddUserInfoFragment"),
}

// ContentActivity에서 보여줄 프래그먼트들의 이름
enum class ContentFragmentName(var str:String){
    A_FRAGMENT("a"),
    B_FRAGMENT("b"),
}