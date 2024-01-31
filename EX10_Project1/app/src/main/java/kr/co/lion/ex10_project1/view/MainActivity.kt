package kr.co.lion.ex10_project1.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.ex10_project1.R
import kr.co.lion.ex10_project1.adapter.MainAdapter
import kr.co.lion.ex10_project1.databinding.ActivityMainBinding
import kr.co.lion.ex10_project1.model.Student

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val studentList = ArrayList<Student>()

    private lateinit var secondActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCallBackSecondActivity()
        initRv()
        initToolbar()
    }

    // MainActivity의 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_row, menu)

        menu?.findItem(R.id.menu_main_row_item_delete)?.setOnMenuItemClickListener {
            // adapterPosition 번째 객체를 리스트에서 제거한다.
            studentList.removeAt(adapterPosition)
            // RecyclerView를 갱신한다.
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun initToolbar(){
        binding.apply {
            toolbar.apply {
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.add -> {
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            secondActivityLauncher.launch(intent)
                            true
                        }
                        else -> true
                    }
                }
            }
        }
    }

    private fun setCallBackSecondActivity(){
        val contact = ActivityResultContracts.StartActivityForResult()

        secondActivityLauncher = registerForActivityResult(contact){
            if(it.resultCode == RESULT_OK){
                if (it.data != null){
                    val newStudent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it.data!!.getParcelableExtra("student", Student::class.java)
                    }else{
                        it.data!!.getParcelableExtra("student")
                    }

                    studentList.add(newStudent!!)
                    binding.rv.adapter?.notifyItemInserted(studentList.size - 1 )
                }


            }
        }
    }


    fun initRv(){
        binding.apply {
            val adapter = MainAdapter(studentList)
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            adapter.itemClick = object : MainAdapter.ItemClick{
                override fun onClick(view: View, position: Int) {
                    val chk1 = if(studentList.size == 0){
                        false
                    } else {
                        true
                    }

                    if (chk1){
                        val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                        intent.putExtra("select", studentList[position])
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@MainActivity, "학생 정보를 추가 해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}