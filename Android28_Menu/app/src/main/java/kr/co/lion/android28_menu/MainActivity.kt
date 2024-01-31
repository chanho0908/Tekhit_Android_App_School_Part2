package kr.co.lion.android28_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android28_menu.databinding.ActivityMainBinding
import kr.co.lion.android28_menu.databinding.RowBinding

// Context Menu : View를 길게 누르면 타나타는 메뉴
// 주의할점 : 이 메서드 내부에서는 어떤 뷰의 메뉴인지를 구분할 수가 없다.
// 메뉴 아이템의 아이디를 정해줄때 모두 다르게 정해주고
// 이 메서드 내부에서 안에서는 꼭 주석으로 누구의 메뉴인지를 명시해주세요

// Popup Menu : 개발자가 코드를 통해 원하는 View에 띄우는 메뉴
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rv.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // textView의 컨텍스트 메뉴
            // 첫 번째 : 메뉴를 구성하기 위해 사용할 메뉴 객체
            textView.setOnCreateContextMenuListener { menu, view, contextMenuInfo ->
                // 메뉴의 헤더
                menu?.setHeaderTitle("텍스트 뷰의 메뉴입니다.")
                menuInflater.inflate(R.menu.main_menu, menu)

                // 각 메뉴 아이템을 추출하여 리스너를 설정해준다.
                menu?.findItem(R.id.textview_menu_item1)?.setOnMenuItemClickListener {
                    textView.text = "텍스트뷰의 메뉴 항목1 선택"
                    true
                }

                // 각 메뉴 아이템을 추출하여 리스너를 설정해준다.
                menu?.findItem(R.id.textview_menu_item2)?.setOnMenuItemClickListener {
                    textView.text = "텍스트뷰의 메뉴 항목2 선택"
                    true
                }
            }

            button.setOnClickListener {
                // 팝업 메뉴를 생성한다.
                // 두 번째 매개변수 : 메뉴를 띄울 View를 지정한다.
                val popUpMenu = PopupMenu(this@MainActivity, textView)
                // 메뉴를 구성한다.
                menuInflater.inflate(R.menu.popup_menu, popUpMenu.menu)
                // 메뉴를 보여준다.
                popUpMenu.show()
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            val rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding

                rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // RecyclerView 항목에 컨텍스트 메뉴를 설정해준다.
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu?.setHeaderTitle("${adapterPosition}번째 항목의 메뉴")
                    menuInflater.inflate(R.menu.rv_menu, menu)

                    menu.findItem(R.id.recyclerview_menu_item1).setOnMenuItemClickListener {
                        binding.textView.text = "${adapterPosition}번 째 메뉴 1 클릭"
                        true
                    }

                    menu.findItem(R.id.recyclerview_menu_item2).setOnMenuItemClickListener {
                        binding.textView.text = "${adapterPosition}번 째 메뉴 2 클릭"
                        true
                    }
                }
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            return ViewHolderClass(rowBinding)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewRow.text = "항목 $position"
        }
    }
}