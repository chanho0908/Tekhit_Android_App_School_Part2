package kr.co.lion.androidproject1test

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject1test.databinding.ActivityMainBinding
import kr.co.lion.androidproject1test.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Activity 런처
    lateinit var inputActivityLauncher : ActivityResultLauncher<Intent>
    lateinit var showActivityLauncher : ActivityResultLauncher<Intent>

    // RecycerView를 구성하기 위한 리스트
    val recyclerViewList = mutableListOf<Animal>()
    // 현재 항목을 구성하기 위해 사용한 객체가 Util.animalList의 몇번째 객체인지를 담을 리스트
    val recyclerViewIndexList = mutableListOf<Int>()
    // 현재 선택되어 있는 필터 타입
    lateinit var filterType: FilterType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 필터 초기 값
        filterType = FilterType.FILTER_TYPE_ALL

        setLauncher()
        setToolbar()
        setView()
        setRvList()
        setEvent()
    }

    // 런처 설정
    fun setLauncher(){
        // InputActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){

        }

        // ShowActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(contract2){

        }
    }

    override fun onResume() {
        super.onResume()
        activityMainBinding.apply {
            // 리사이클러뷰 갱신
            recyclerViewMain.adapter?.notifyDataSetChanged()

        }
    }

    // 툴바 설정
    fun setToolbar(){

        activityMainBinding.apply {
            materialToolbar.apply {
                // 타이틀
                title = "동물원 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {

                    when(it.itemId){
                        // 필터 메뉴
                        R.id.menu_item_main_filter -> {
                            // 필터 선택을 위한 다이얼로그를 띄운다.
                            showFilterDialog()
                        }
                    }

                    true
                }
            }
        }
    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어뎁터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 이벤트 설정
    fun setEvent(){
        activityMainBinding.apply {
            // FloatActionButton
            fabMainAdd.setOnClickListener {
                // InputActivity를 실행한다.
                val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                inputActivityLauncher.launch(inputIntent)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolder
        inner class ViewHolderMain (rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return recyclerViewList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
//            when(position % 3){
//                0 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "사자"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
//                }
//                1 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "호랑이"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
//                }
//                2 -> {
//                    holder.rowMainBinding.textViewRowMainName.text = "기린"
//                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
//                }
//            }
            // position 번째 객체를 추출한다.
            val animal = recyclerViewList[position]
            // 동물의 이름을 설정한다.
            holder.rowMainBinding.textViewRowMainName.text = animal.name
            // 동물 종류별로 분기한다.
            when(animal.type){
                // 사자
                AnimalType.ANIMAL_TYPE_LION -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
                }
                // 호랑이
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
                }
                // 기린
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
                }
            }

            // 항목을 누르면 ShowActivity를 실행한다.
            holder.rowMainBinding.root.setOnClickListener {
                val showIntent = Intent(this@MainActivity, ShowActivity::class.java)
                // 현재 번째의 순서값을 담아준다.
                showIntent.putExtra("position", position)

                showActivityLauncher.launch(showIntent)
            }
        }
    }

    // 필터 다이얼로그를 띄우는 메서드
    fun showFilterDialog(){
        val dialogBuilder = MaterialAlertDialogBuilder(this@MainActivity)
        dialogBuilder.setTitle("필터 선택")

        // 항목
        val itemArray = arrayOf("전체", "사자", "호랑이", "기린")
        dialogBuilder.setItems(itemArray){ dialogInterface: DialogInterface, i: Int ->
            // 리스너의 두번째 매개변수에는 사용자가 선택한 다이얼로그 항목의 순서값이 전달된다.
            // 선택한 항목에 대한 필터 값을 설정
            filterType = when(i){
                0 -> FilterType.FILTER_TYPE_ALL
                1 -> FilterType.FILTER_TYPE_LION
                2 -> FilterType.FILTER_TYPE_TIGER
                3 -> FilterType.FILTER_TYPE_GIAFFE
                else -> FilterType.FILTER_TYPE_ALL
            }

            setRvList()
            activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
        }

//        dialogBuilder.setMultiChoiceItems(itemArray, booleanArrayOf(true, false, true, false)){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
//
//        }

//        dialogBuilder.setSingleChoiceItems(itemArray, 0){ dialogInterface: DialogInterface, i: Int ->
//
//        }

        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.show()
    }

    // 검색 필터에 따라 리스트에 데이터를 담아 주는 메소드
    fun setRvList(){
        recyclerViewList.clear()
        recyclerViewIndexList.clear()

        // 필터에 따라 분기
        // 검색 필터에 따라 리스트에 데이터를 담아주는 메서드
        fun setRecyclerViewList(){
            // 필터에 따라 분기한다.
            when(filterType){
                // 전체
                FilterType.FILTER_TYPE_ALL -> {
                    Util.animalList.forEachIndexed { index, animal ->
                        recyclerViewList.add(animal)
                        recyclerViewIndexList.add(index)
                    }
                }
                // 사자
                FilterType.FILTER_TYPE_LION -> {
                    // 동물 타입이 사자인 것만 담는다.
                    Util.animalList.forEachIndexed { index, animal ->
                        if (animal.type == AnimalType.ANIMAL_TYPE_LION){
                            recyclerViewList.add(animal)
                            recyclerViewIndexList.add(index)
                        }
                    }
                }
                // 호랑이
                FilterType.FILTER_TYPE_TIGER -> // 동물 타입이 호랑이인 것만 담는다.
                    Util.animalList.forEachIndexed { index, animal ->
                        if (animal.type == AnimalType.ANIMAL_TYPE_TIGER){
                            recyclerViewList.add(animal)
                            recyclerViewIndexList.add(index)
                        }
                    }
                // 기린
                FilterType.FILTER_TYPE_GIAFFE -> {
                    Util.animalList.forEachIndexed { index, animal ->
                        if (animal.type == AnimalType.ANIMAL_TYPE_GIRAFFE){
                            recyclerViewList.add(animal)
                            recyclerViewIndexList.add(index)
                        }
                    }
                }
            }
        }
    }
}