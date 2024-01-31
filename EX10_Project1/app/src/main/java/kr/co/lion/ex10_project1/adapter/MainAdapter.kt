package kr.co.lion.ex10_project1.adapter

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex10_project1.R
import kr.co.lion.ex10_project1.databinding.MainItemBinding
import kr.co.lion.ex10_project1.model.Student

class MainAdapter(val dataSet: List<Student>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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
            textViewGrade.text = dataSet[position].grade.toString()
            textViewKor.text = dataSet[position].kor.toString()
            textViewEng.text = dataSet[position].eng.toString()
            textViewMath.text = dataSet[position].math.toString()
        }
    }
}