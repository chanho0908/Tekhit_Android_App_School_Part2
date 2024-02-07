package kr.co.lion.android_mini_project_1.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android_mini_project_1.R
import kr.co.lion.android_mini_project_1.adapter.MainRvAdapter
import kr.co.lion.android_mini_project_1.databinding.ActivityMainBinding
import kr.co.lion.androidproject1test.Animal
import kr.co.lion.androidproject1test.Lion

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // Activity 런처
    lateinit var inputActivityLauncher : ActivityResultLauncher<Intent>
    lateinit var showActivityLauncher : ActivityResultLauncher<Intent>
    private val animalList = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animalList.add(Lion("라이언", 20, 20, "남자"))
        setLauncher()
        setToolbar()
        setView()
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

    // 툴바 설정
    fun setToolbar(){

        binding.apply {
            materialToolbar.apply {
                // 타이틀
                title = "동물원 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
            }
        }
    }

    // View 설정
    fun setView(){
        binding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어뎁터
                adapter = MainRvAdapter(animalList){
                    val intent = Intent(this@MainActivity, ShowActivity::class.java)
                    intent.putExtra("selectedAnimal", animalList[it])
                    showActivityLauncher.launch(intent)
                }
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
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

        }
        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.show()
    }

    // 이벤트 설정
    fun setEvent(){
        binding.apply {
            // FloatActionButton
            fabMainAdd.setOnClickListener {
                // InputActivity를 실행한다.
                val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                inputActivityLauncher.launch(inputIntent)
            }
        }
    }
}