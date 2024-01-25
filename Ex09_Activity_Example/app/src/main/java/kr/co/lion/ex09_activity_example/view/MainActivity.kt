package kr.co.lion.ex09_activity_example.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex09_activity_example.R
import kr.co.lion.ex09_activity_example.adapter.RecyclerViewAdapter
import kr.co.lion.ex09_activity_example.databinding.ActivityMainBinding
import kr.co.lion.ex09_activity_example.model.Student

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter
    val list = ArrayList<Student>()

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it != null){
            when(it.resultCode){
                RESULT_OK ->{
                    if(it.data != null){
                        val studentName = it.data!!.getStringExtra("studentName").toString()
                        val studentGrade = it.data!!.getStringExtra("studentGrade").toString()
                        val studentKor = it.data!!.getStringExtra("studentKor").toString()
                        val studentEng = it.data!!.getStringExtra("studentEng").toString()
                        val studentMath = it.data!!.getStringExtra("studentMath").toString()

                        val newStudent = Student(studentName, studentGrade, studentKor, studentEng, studentMath)

                        list.add(newStudent)
                        Log.d("dasdas", "newStudent $list")
                        adapter.notifyItemInserted(list.size)
                    }
                }
            }
        }
    }

    val buttonClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.studentInfoBtn -> {
                Log.d("sdsaddsd", "클릭")
                launcher.launch(Intent(this, SecActivity::class.java))
            }

            R.id.scoreBtn -> {
                val intent = Intent(this, ThirdActivity::class.java)
                val calcResult = calculateScore()
                intent.putIntegerArrayListExtra("calcResult", calcResult)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        binding.apply {
            studentInfoBtn.setOnClickListener(buttonClickListener)
            scoreBtn.setOnClickListener(buttonClickListener)
        }
    }

    private fun setRecyclerView() {
        adapter = RecyclerViewAdapter(list)

        list.add(Student("인직", "3", "10","20","50"))

        binding.apply {
            val deco = MaterialDividerItemDecoration(rv.context, LinearLayoutManager.VERTICAL)
            rv.addItemDecoration(deco)

            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            adapter.itemClick = object : RecyclerViewAdapter.ItemClick{
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@MainActivity, FourthActivity::class.java)

                    intent.putExtra("storeName", list[position].name)
                    intent.putExtra("storeGrade", list[position].grade)
                    intent.putExtra("storeKor", list[position].kor.toInt())
                    intent.putExtra("storeEng", list[position].eng.toInt())
                    intent.putExtra("storeMath", list[position].math.toInt())

                    startActivity(intent)
                }
            }
        }

    }

    fun calculateScore(): ArrayList<Int> {
        val result = ArrayList<Int>()
        try{
            var totalKor = 0
            var totalEng = 0
            var totalMath = 0

            list.forEach { student ->
                totalKor += student.kor.toInt()
                totalEng += student.eng.toInt()
                totalMath += student.math.toInt()
            }

            result.add(totalKor)
            result.add(totalKor / list.size)
            result.add(totalEng)
            result.add(totalEng / list.size)
            result.add(totalMath)
            result.add(totalMath / list.size)
            result.add(totalKor + totalEng + totalMath)
            result.add(totalKor + totalEng + totalMath / 3)

        }catch (e: Exception){
            Log.d("MainActivity", e.toString())
        }
        return result
    }
}