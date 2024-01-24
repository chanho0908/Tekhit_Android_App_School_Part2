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

// notifyDataSetChange() : 리스트의 데이터가 변하게 되면 호출하여 리사이클러뷰를 갱신
// notifyDataSetChange() 를 호출하게 되면 리스트의 모든 데이터를
// 다시 처음부터 새로운 객체를 생성 하여 랜더링 하기 때문에 비용이 크게 발생한다.
// DiffUtil은 이전 데이터와 현재 데이터 목록의 차이를 계산하여
// 업데이트 해야할 데이터에 대해서만 갱신
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

            // 콜백 클래스 (DiffUtilCallback)를 전달하여 업데이트가 필요한 리스트를 확인
            val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(infoList, newList))
            // 기존 리스트를 clear
            infoList.clear()
            // 한 컬렉션의 모든 요소를 다른 컬렉션에 추가
            infoList.addAll(newList)
            // 부분적으로 업데이트를 실행
            diffResult.dispatchUpdatesTo(this)
        }
    }
}