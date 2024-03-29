package kr.co.lion.android20_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android20_recyclerview.databinding.ActivityMainBinding
import kr.co.lion.android20_recyclerview.databinding.RowBinding

// AdapterView : 무한개의 항목을 보여주는 목적으로 사용하는 뷰들..
// Adapter를 사용하기 때문에 AdapterView라고 부른다.

// Adapter : View를 구성하기 위해 필요한 정보를 가지고 있는 요소

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // 이미지의 id
    val imageRes = arrayOf(
        R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3,
        R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
        R.drawable.imgflag7
    )

    // 문자열1
    val textData1 = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질"
    )

    // 문자열2
    val textData2 = arrayOf(
        "탈락", "진출", "탈락", "진출", "탈락", "진출", "진출"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val adapter = RecyclerViewAdapter()

            rv.adapter = adapter
            // RecyclerView 의 항목을 보여줄 방식을 설정한다.
            // 위에서 아래 방향
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            // RecyclerView Decoration
            // 각 항목을 구분하기 위한 구분선
            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            // 구분선 좌측 여백
            // deco.dividerInsetStart = 50
            // 구분선 우측 여백
            // deco.dividerInsetEnd = 50

            rv.addItemDecoration(deco)

        }
    }

    // Adapter
    // RecyclerView 구성을 위해 필요한 코드들이 작성되어 있다.
    // 1. 클래스를 작성한다
//    inner class RecyclerViewAdapter{
//
//    }

    // 2. ViewHolder 클래스를 작성해준다.
    // ViewHolder : View의 id를 가지고 있는 요소
//    inner class RecyclerViewAdapter{
//
//        // ViewHolder
//        inner class ViewHolderClass(rowBinding:RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
//            // 매개변수로 들어오는 바인딩객체를 담을 프로퍼티
//            var rowBinding:RowBinding
//
//            init{
//                this.rowBinding = rowBinding
//            }
//        }
//    }

    // 3. adapter 클래를 Adapter 를 상속받게 구현해준다.
    // 필요한 메서드들을 구현해준다.

    // 라사이클러뷰를 만들 때 개발자가 생각해야 하는 3가지 요소
    //1. 항목의 개수
    //2. 항목 하나를 어떠한 모습을 어떻게 보여 줄 것인지
    //3. 어떠한 데이터를 보여줄 것 인가 보여줄 데이터
    //
    // 이세가지를 RecyclerView를 상속하여 구현할 수 있다.
    //
    // getImtemCount : 보여줄 항목이 몇 개인가
    //
    // oncreateViewholer : 어떠한 모습으로 보여줄것인가
    //
    // onBindViewHolder : 어떤 데이터를 보여줄것인가

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // ViewHolder
        inner class ViewHolderClass(rowBinding:RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            // 매개변수로 들어오는 바인딩객체를 담을 프로퍼티
            var rowBinding:RowBinding

            init{
                this.rowBinding = rowBinding

                // 현재 항목을 누르면 반응하는 리스너
                // 눈에 보여지는 항목에 클릭 이벤트를 붙혀준다.
                this.rowBinding.root.setOnClickListener {
                    binding.textView.text = "선택한 항목 : ${textData1[adapterPosition]}"
                }

                // View의 가로 길이는 최대로
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
            }


        }

        // ViewHolder 객체를 생성하여 반환한다.
        // 새롭게 항목이 보여질 때 재사용 가능한 항목이 없다면 이 메서드를 호출한다.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // View Binding
            // layoutInflater도 Activity가 가지고 있는 프로퍼티다.
            // RecyclerViewAdapter는 MainActivity의 inner class로 정의했다.
            // 그렇기 때문에 내부 클래스는 외부 클래스에 정의된 멤버를 모두 사용할 수 있다.
            val rowBinding = RowBinding.inflate(layoutInflater)
            // View Holder
            val viewHolderClass = ViewHolderClass(rowBinding)

            // 반환한다.
            return viewHolderClass
        }
        // RecyclerView를 통해 보여줄 항목 전체의 개수를 반환한다.
        override fun getItemCount(): Int {
            return imageRes.size
        }
        // 항목의 View에 보여주고자 하는 데이터를 설정한다.
        // 첫 번째 매개변수 : ViewHolder 객체. 재사용 가능한 것이 없다면 onCreateViewHolder 메서드를
        // 호출하고 반환하는 ViewHolder 객체가 들어오고 재사용 가능한 것이 있다면 재사용 가능한 ViewHolder 객체가
        // 들어온다.
        // 두 번째 매개변수 : 구성하고자 하는 항목의 순서 값(0 부터 1씩 증가)
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.imageViewRow.setImageResource(imageRes[position])
            holder.rowBinding.textViewRow1.text = textData1[position]
            holder.rowBinding.textViewRow2.text = textData2[position]
        }
    }
}