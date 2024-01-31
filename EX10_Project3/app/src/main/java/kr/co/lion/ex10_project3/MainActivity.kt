package kr.co.lion.ex10_project3

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex10_project3.databinding.ActivityMainBinding
import kr.co.lion.ex10_project3.databinding.RowMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>

    private lateinit var showInfoActivityLauncher: ActivityResultLauncher<Intent>

    val studentList = ArrayList<StudentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setToolbar()
        setView()
    }

    // 기본 데이터 및 객체 셋팅
    fun initData(){
        val contact1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contact1){
        if(it.resultCode == RESULT_OK){
            // 전달된 Intent객체가 있다면
            if(it.data != null){
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                    val studentData = it.data?.getParcelableExtra("studentData", StudentData::class.java)
                    studentList.add(studentData!!)
                    binding.recyclerViewMain.adapter?.notifyItemInserted(studentList.size)
                }else{
                    val studentData = it.data?.getParcelableExtra<StudentData>("studentData")
                    studentList.add(studentData!!)
                    binding.recyclerViewMain.adapter?.notifyItemInserted(studentList.size)
                }
            }
        }

        }

        val contact2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contact2){

        }
    }

    fun setToolbar(){
        binding.apply {
            toolbarMain.apply {
                toolbarMain.title = "학생정보관리"

                inflateMenu(R.menu.menu_main)

                setOnMenuItemClickListener{
                    when(it.itemId){
                        R.id.menu_main_add -> {
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    // View 구성
    fun setView(){
        binding.apply {
            recyclerViewMain.apply {
                // 어뎁터 설정
                adapter = MainRvAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 구분선
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    inner class MainRvAdapter: RecyclerView.Adapter<MainRvAdapter.MainViewHolder>(){
        inner class MainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                this.rowMainBinding.root.setOnClickListener {
                    // ShowInfoActivity를 실행한다.
                    val showInfoIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)

                    showInfoActivityLauncher.launch(showInfoIntent)
                }

                this.rowMainBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menuInflater.inflate(R.menu.menu_main_row, menu)

                    menu?.findItem(R.id.menu_main_row_item_delete)?.setOnMenuItemClickListener {
                        studentList.removeAt(adapterPosition)
                        binding.recyclerViewMain.adapter?.notifyItemRemoved(adapterPosition)

                        true
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(RowMainBinding.inflate(layoutInflater))
        }

        override fun getItemCount() = studentList.size

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "${studentList[position].name}"
            holder.rowMainBinding.textViewRowMainGrade.text = "${studentList[position].grade}학년"
        }
    }
}