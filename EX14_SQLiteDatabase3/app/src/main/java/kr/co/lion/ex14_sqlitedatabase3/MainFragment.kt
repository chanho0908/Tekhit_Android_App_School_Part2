package kr.co.lion.ex14_sqlitedatabase3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex14_sqlitedatabase3.databinding.FragmentMainBinding
import kr.co.lion.ex14_sqlitedatabase3.databinding.RowMainBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 메모 객체를 담을 리스트
    lateinit var memoList:MutableList<MemoModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingData()
        settingView()

        return fragmentMainBinding.root
    }

    // 데이터를 가져온다.
    fun settingData(){
        memoList = MemoDao.selectAllMemo(mainActivity)
    }

    // view 설정
    fun settingView(){
        fragmentMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어뎁터
                adapter = RecyclerViewMainAdapter()
                // LayoutManager
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.MainViewHolder>(){
        // ViewHolder
        inner class MainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(rowMainBinding)

            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMain.text = memoList[position].memoSubject

            // 항목을 누를때..
            holder.rowMainBinding.root.setOnClickListener {
                // BottomSheet를 띄운다.
                val mainSheetFragment = MainSheetFragment()

                // position 번째 메모의 idx값을 Fragment로 전달해준다.
                val sheetBundle = Bundle()
                sheetBundle.putInt("memoIdx", memoList[position].memoIdx)
                mainSheetFragment.arguments = sheetBundle

                mainSheetFragment.show(mainActivity.supportFragmentManager, "BottomSheet")
            }
        }
    }

    // recyclerView 갱신
    fun reloadRecyclerView(){
        // 데이터를 읽어온다.
        memoList = MemoDao.selectAllMemo(mainActivity)
        // RecyclerView를 갱신한다.
        fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
    }
}












