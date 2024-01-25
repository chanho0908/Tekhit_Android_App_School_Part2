package kr.co.lion.ex08_recyclerview_filter.useDiffUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex08_recyclerview_filter.databinding.MainItemBinding


class MainViewAdapter2(var dataList: ArrayList<String>): RecyclerView.Adapter<MainViewAdapter2.MainViewHolder>() {
    inner class MainViewHolder(val binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.textViewName.text = dataList[position]
    }

    fun updateData(newList: List<String>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = dataList.size
            override fun getNewListSize(): Int = newList.size

            // 객체의 프로퍼티까지 같냐
            // 객체의 고유값 id
            //EX 상품 데이터클래스 -> 상품번호 등 pkey처럼 구분할 수 있는 값
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataList[oldItemPosition] == newList[newItemPosition]
            }

            // 메모리상의 객체까지
            // 객체 자체를 비교
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataList[oldItemPosition] == newList[newItemPosition]
            }
        })

        dataList.clear()
        dataList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }
}