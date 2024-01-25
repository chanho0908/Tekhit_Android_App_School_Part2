package kr.co.lion.ex08_recyclerview_filter.unUseDiffUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex08_recyclerview_filter.databinding.MainItemBinding


class MainViewAdapter(var dataList: ArrayList<String>): RecyclerView.Adapter<MainViewAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.textViewName.text = dataList[position]
    }

    // 9. RecyclerView에 전달되는 리스트를
    // 모든 이름을 담은 리스트 < -> 검색어를 담은 리스트
    // 로 변환하는 함수
    fun changeRvList(newList: ArrayList<String>){
        // 10. 전달 받은 리스트에 따라 기존 리스트를 변경
        dataList = newList

        // 11. RecyclerView Adapter에 데이터가 변경되었음을 알림
        notifyDataSetChanged()
    }
}