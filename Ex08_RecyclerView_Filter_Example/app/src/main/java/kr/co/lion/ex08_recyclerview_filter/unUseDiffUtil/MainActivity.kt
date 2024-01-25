package kr.co.lion.ex08_recyclerview_filter.unUseDiffUtil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.ex08_recyclerview_filter.databinding.ActivityMainBinding

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

    // 리사이클러뷰 초기 세팅
    fun initRecyclerView(){
        nameList.add("찬호")

        // 1. RecyclerView Adapter 객체 생성
        adapter = MainViewAdapter(nameList)
        // 2. adapter 연결
        binding.rv.adapter = adapter
        // 3. layoutManager 연결
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    // 버튼 클릭 리스너 생성 (람다식)
    val clickListener = OnClickListener {
        if (binding.name.text != null){
            nameList.add(binding.name.text.toString())
            adapter.notifyItemInserted(nameList.size - 1)
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

                // 2.1 검색어가 비어있지 않으면
                if (userSearch != ""){

                    // 3.1 changeRvData 함수에 검색어 전달
                    changeRvData(userSearch)

                } else{
                    // 2.2 검색어가 비어있다면 모든 데이터가 담김
                    // 기존 리스트를 전달
                    adapter.changeRvList(nameList)
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

            // 6. 검색어와 같은 이름이 있다면
            if (name.contains(searchText)) {

                // 7. 검색어 리스트에 저장
                searchList.add(name)
            }
        }

        // 8. 어댑터에 새로운 리스트를 전달
        adapter.changeRvList(searchList)
    }
}