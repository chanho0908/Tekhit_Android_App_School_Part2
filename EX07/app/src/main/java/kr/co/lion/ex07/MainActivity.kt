package kr.co.lion.ex07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex07.databinding.ActivityMainBinding
import kr.co.lion.ex07.databinding.MainItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val infoList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoList.add(DataModel("1", "찬호"))

        binding.apply {
            val adapter = MainRvAdapter()
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            button.setOnClickListener {
                val id = editTextId.text.toString()
                val name =  editTextName.text.toString()
                addItem(id, name, adapter)
            }
        }
    }

    fun addItem(id: String, name: String, adapter: MainRvAdapter){
        // 기존 List에 사용자가 입력한 값을 더해 새로운 List 생성
        val newList: ArrayList<DataModel> = ArrayList(infoList.map { it.copy() })
        newList.add(DataModel(id, name))
        adapter.submitList(newList)
    }

    inner class MainRvAdapter: RecyclerView.Adapter<MainRvAdapter.MainViewHolder>(){

        inner class MainViewHolder(val binding: MainItemBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(MainItemBinding.inflate(layoutInflater))
        }

        override fun getItemCount(): Int{
            Log.d("sadasdsa", infoList.size.toString())
            return infoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val binding = holder.binding
            binding.apply {
                textViewId.text = infoList[position].id
                textViewName.text = infoList[position].name
            }
        }

        fun submitList(newList: ArrayList<DataModel>){
            val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(infoList, newList))
            infoList.clear()
            infoList.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }
    }
}