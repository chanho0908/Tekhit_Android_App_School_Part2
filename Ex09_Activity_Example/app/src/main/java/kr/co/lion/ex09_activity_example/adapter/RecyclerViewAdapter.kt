package kr.co.lion.ex09_activity_example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex09_activity_example.databinding.MainItemBinding
import kr.co.lion.ex09_activity_example.model.Student

class RecyclerViewAdapter(val dataSet: ArrayList<Student>): RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder>() {

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick ?= null

    inner class MainViewHolder(val binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding

        binding.root.setOnClickListener { v->
            itemClick?.onClick(v, position)
        }

        binding.apply {
            textViewName.text = dataSet[position].name
            textViewGrade.text = dataSet[position].grade
            textViewKor.text = dataSet[position].kor
            textViewEng.text = dataSet[position].eng
            textViewMath.text = dataSet[position].math
        }
    }


}