package kr.co.lion.ex14_sqlitedatabase3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.ex14_sqlitedatabase3.databinding.FragmentMainSheetBinding


class MainSheetFragment : BottomSheetDialogFragment() {

    lateinit var fragmentMainSheetBinding: FragmentMainSheetBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMainSheetBinding = FragmentMainSheetBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingView()

        return fragmentMainSheetBinding.root
    }

    // View 설정
    fun settingView(){
        // 데이터를 추출한다.
        val memoIdx = arguments?.getInt("memoIdx")!!
        // 데이터베이스에서 메모 내용을 가져온다.
        val memoModal = MemoDao.selectOneMemo(mainActivity, memoIdx)

        // 출력한다.
        fragmentMainSheetBinding.apply {
            textViewSheetSubject.text = memoModal.memoSubject
            textViewSheetText.text = memoModal.memoText

            // 버튼
            buttonSheetDelete.setOnClickListener {
                // memoIdx 메모를 삭제한다.
                MemoDao.deleteMemo(mainActivity, memoIdx)
                // RecyclerView를 갱신한다.
                mainActivity.reloadRecyclerView()
                // 내려준다.
                dismiss()
            }
        }
    }

}