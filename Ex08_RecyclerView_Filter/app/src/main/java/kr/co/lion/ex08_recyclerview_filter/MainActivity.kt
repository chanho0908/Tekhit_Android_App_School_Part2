package kr.co.lion.ex08_recyclerview_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.ex08_recyclerview_filter.databinding.ActivityMainBinding
import kr.co.lion.ex08_recyclerview_filter.databinding.MainItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainViewAdapter

    val nameList = ArrayList<String>()
    val searchList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        setEventListener()
    }

    fun initRecyclerView(){
        nameList.add("찬호")

        adapter = MainViewAdapter(nameList)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    val clickListener = OnClickListener {
        if (binding.name.text != null){
            nameList.add(binding.name.text.toString())
            adapter.notifyItemInserted(nameList.size - 1)
        }else{
            Toast.makeText(this, "이름을 입력하랑께?", Toast.LENGTH_SHORT).show()
        }
    }

    fun setEventListener(){
        binding.apply {
            button.setOnClickListener(clickListener)

            searchText.addTextChangedListener {
                val userSearch = it.toString()

                if (userSearch != ""){
                    nameList.forEach { name ->
                        if (userSearch == name){
                            filterData(userSearch)
                        }
                    }
                } else{
                    adapter.filteringList(nameList)
                }

            }
        }
    }

     fun filterData(searchText: String) {
        searchList.clear()

        nameList.forEach { name ->
            if (name.contains(searchText)) {
                searchList.add(name)
            }
        }

        adapter.filteringList(searchList)
    }

//    fun setAdapter(list: ArrayList<String>){
//        adapter = MainViewAdapter(list)
//        binding.rv.adapter = adapter
//        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
//    }
}