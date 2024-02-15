package kr.co.lion.ex12_sqlitedatabase1

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
import kr.co.lion.ex12_sqlitedatabase1.databinding.ActivityMainBinding
import kr.co.lion.ex12_sqlitedatabase1.databinding.RowMainBinding
// Model 클래스 : 데이터를 담아두는 클래스들
// Dao 클래스 : 데이터베이스에 관련된 작업을 담아두는 클래스들


class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity의 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>
    // ShowInfoActivity의 런처
    lateinit var showInfoActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolbar()
        setView()
    }

    // 기본 데이터 및 객체 셋팅
    fun initData(){
        // InputActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){

        }

        // ShowInfoActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract2){

        }
    }

    // 툴바 구성
    fun setToolbar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 정보 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                // 메뉴의 리스너
                setOnMenuItemClickListener {
                    // 메뉴의 id로 분기한다
                    when(it.itemId){
                        // 추가 메뉴
                        R.id.menu_main_add -> {
                            // InputActivity를 실행한다.
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
        activityMainBinding.apply {
            // ReyclerView
            recyclerViewMain.apply {
                // 어뎁터 설정
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 구분선
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding:RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init{
                this.rowMainBinding = rowMainBinding
                // 항목 클릭시 전체가 클릭이 될 수 있도록
                // 가로 세로 길이를 설정해준다.
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // 항목을 눌렀을 때의 리스너
                this.rowMainBinding.root.setOnClickListener {

                    // ShowInfoActivity를 실행한다.
                    val showInfoIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)
                    showInfoActivityLauncher.launch(showInfoIntent)
                }
                // 항목을 길게 눌렀을 때 메뉴를 띄운다.
                this.rowMainBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    // 메뉴를 구성한다.
                    menuInflater.inflate(R.menu.menu_main_row, menu)
                    // 메뉴 항목을 눌렀을 때
                    menu?.findItem(R.id.menu_main_row_item_delete)?.setOnMenuItemClickListener {

                        true
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "이름 $position"
            holder.rowMainBinding.textViewRowMainGrade.text = "$position 학년"
        }
    }
}