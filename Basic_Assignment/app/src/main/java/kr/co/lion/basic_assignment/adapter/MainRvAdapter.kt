package kr.co.lion.basic_assignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.basic_assignment.databinding.MainItemBinding
import kr.co.lion.basic_assignment.model.DailyMemo
import kr.co.lion.basic_assignment.util.Constant.Companion.fromLongToDateString
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainRvAdapter(val memoList: ArrayList<DailyMemo>): RecyclerView.Adapter<MainRvAdapter.MainViewHolder>() {

    interface ItemClick{
        fun onClick(v: View, position: Int)
    }

    var itemClick: ItemClick ?= null

    inner class MainViewHolder(val binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int{
        return memoList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding

        binding.root.setOnClickListener { v->
            itemClick?.onClick(v, position)
        }

        binding.textViewTitle.text = memoList[position].title
        binding.textViewDate.text = fromLongToDateString(memoList[position].day)

    }
}