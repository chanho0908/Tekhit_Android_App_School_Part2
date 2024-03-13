package com.myproject.my_board_project.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.my_board_project.model.UserJoinModel

class JoinViewModel: ViewModel() {
    private val _userJoinData = MutableLiveData<UserJoinModel>()
    val userJoinData: LiveData<UserJoinModel> get() =  _userJoinData

    // 툴바의 타이틀
    private val _toolbarJoinTitle = MutableLiveData<String>()
    val toolbarJoinTitle: LiveData<String> get() = _toolbarJoinTitle

    private val _toolbarJoinNavigationIcon = MutableLiveData<Int>()
    val toolbarJoinNavigationIcon: LiveData<Int> get() = _toolbarJoinNavigationIcon
}