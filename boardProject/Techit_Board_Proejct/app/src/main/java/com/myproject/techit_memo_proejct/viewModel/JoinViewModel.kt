package kr.co.lion.androidproject4boardapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.androidproject4boardapp.models.JoinModel

class JoinViewModel: ViewModel() {
    //아이디
    private var _joinInfo = MutableLiveData<JoinModel>()
    val joinInfo: LiveData<JoinModel> get() = _joinInfo



}