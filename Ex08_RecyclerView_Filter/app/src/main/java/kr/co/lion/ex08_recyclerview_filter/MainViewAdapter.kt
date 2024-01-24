package kr.co.lion.ex08_recyclerview_filter

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

    fun filteringList(searchList: ArrayList<String>){
        dataList = searchList
        notifyDataSetChanged()
    }
}