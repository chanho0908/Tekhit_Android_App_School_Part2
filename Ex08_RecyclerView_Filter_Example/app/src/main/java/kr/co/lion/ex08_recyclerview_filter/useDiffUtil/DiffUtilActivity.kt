package kr.co.lion.ex08_recyclerview_filter.useDiffUtil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.ex08_recyclerview_filter.R
import kr.co.lion.ex08_recyclerview_filter.databinding.ActivityDiffUtilBinding

class DiffUtilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiffUtilBinding
    lateinit var adapter: MainViewAdapter2

    val nameList = ArrayList<String>()
    val searchList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiffUtilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        setEventListener()
    }

    // 리사이클러뷰 초기 세팅
    fun initRecyclerView(){
        nameList.add("찬호")

        // 1. RecyclerView Adapter 객체 생성
        adapter = MainViewAdapter2(nameList)
        // 2. adapter 연결
        binding.rv.adapter = adapter
        // 3. layoutManager 연결
        binding.rv.layoutManager = LinearLayoutManager(this@DiffUtilActivity)
    }

    // 버튼 클릭 리스너 생성 (람다식)
    val clickListener = OnClickListener {
        binding.apply {
            if (!name.text.isNullOrEmpty()) {
                nameList.add(name.text.toString())
                adapter.notifyItemInserted(nameList.size - 1)
            }
        }
    }

    // 이벤트 리스너 적용
    fun setEventListener(){
        binding.apply {
            // 버튼 클릭 이벤트 설정
            button.setOnClickListener(clickListener)

            // 텍스트 변화할 때 동작할 리스너
            searchText.addTextChangedListener {

                // 1. 사용자가 입력한 검색어를 가져와서
                val userSearch = it.toString()

                // 2. 검색어가 비어있지 않으면
                if (userSearch.isNotEmpty()){

                    // 3. changeRvData 함수에 검색어 전달
                    changeRvData(userSearch)

                } else{
                    Log.d("sadasdasd", "nameList : $nameList")
                    adapter.updateData(nameList)
                }

            }
        }
    }

    // Recyclerview에 전달되는 List를 변경하는 함수
    fun changeRvData(searchText: String) {
        // 4. 이전에 검색어로 저장된 것들을 초기화
        searchList.clear()

        // 5. 기존에 이름을 저장했던 리스트에서
        nameList.forEach { name ->
            Log.d("sadasdasd", "name : $name")
            // 6. 검색어와 같은 이름이 있다면
            if (name.contains(searchText)) {

                // 7. 검색어 리스트에 저장
                searchList.add(name)
            }
        }

        // 8. 어댑터에 새로운 리스트를 전달
        adapter.updateData(searchList)
    }
}

