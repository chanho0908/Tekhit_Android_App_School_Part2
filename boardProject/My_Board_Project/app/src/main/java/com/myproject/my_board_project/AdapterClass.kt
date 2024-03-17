package com.myproject.my_board_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myproject.my_board_project.databinding.RowMainBinding
import com.myproject.my_board_project.databinding.RowReadContentReplayBinding

class MainRecyclerViewAdapter: RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(RowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = 100

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding
        with(binding){
            textViewRowMainSubject.text = "제목 $position"
            textViewRowMainNickName.text = "작성자 $position"
        }
    }
}

class SearchRecyclerViewAdapter: RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(RowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = 100

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding
        with(binding){
            textViewRowMainSubject.text = "제목 $position"
            textViewRowMainNickName.text = "작성자 $position"
        }
    }
}

class BottomRecyclerViewAdapter : RecyclerView.Adapter<BottomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder =
        BottomViewHolder(RowReadContentReplayBinding.inflate(LayoutInflater.from(parent.context)))


    override fun getItemCount() = 100

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
        val binding = holder.binding
        with(binding){
            textViewRowReplyText.text = "댓글입니다. $position"
            textViewRowReplyNickName.text = "작성자 $position"
            textViewRowReplyDate.text = "2024-03-07"
        }
    }
}

class MainViewHolder(val binding: RowMainBinding): RecyclerView.ViewHolder(binding.root)
class BottomViewHolder(val binding: RowReadContentReplayBinding): RecyclerView.ViewHolder(binding.root)