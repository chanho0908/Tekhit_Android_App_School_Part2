package kr.co.lion.android_mini_project_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android_mini_project_1.databinding.RowMainBinding
import kr.co.lion.android_mini_project_1.model.Animal

class MainRvAdapter(private val animalList: ArrayList<Animal>,
                    private val onClickLister: (Int) -> Unit):
    RecyclerView.Adapter<MainRvAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: RowMainBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val binding = holder.binding

        binding.root.setOnClickListener {
            onClickLister.invoke(position)
        }
    }
}