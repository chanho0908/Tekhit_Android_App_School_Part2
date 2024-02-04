package kr.co.lion.android_mini_project_1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android_mini_project_1.R
import kr.co.lion.android_mini_project_1.databinding.ActivityMainBinding
import kr.co.lion.android_mini_project_1.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // Activity 런처
    lateinit var inputActivityLauncher : ActivityResultLauncher<Intent>
    lateinit var showActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.apply {
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

                // 항목을 누르면 ShowActivity를 실행한다.
                this.rowMainBinding.root.setOnClickListener {
                    val showIntent = Intent(this@MainActivity, ShowActivity::class.java)
                    showActivityLauncher.launch(showIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            return ViewHolderMain(RowMainBinding.inflate(layoutInflater))
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            when(position % 3){
                0 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "사자"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
                }
                1 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "호랑이"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
                }
                2 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "기린"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
                }
            }
        }
    }
}