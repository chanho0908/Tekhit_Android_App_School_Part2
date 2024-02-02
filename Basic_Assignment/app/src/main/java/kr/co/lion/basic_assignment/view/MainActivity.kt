package kr.co.lion.basic_assignment.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.basic_assignment.R
import kr.co.lion.basic_assignment.adapter.MainRvAdapter
import kr.co.lion.basic_assignment.databinding.ActivityMainBinding
import kr.co.lion.basic_assignment.model.DailyMemo
import kr.co.lion.basic_assignment.util.Constant
import kr.co.lion.basic_assignment.util.Constant.Companion.MODIFY_RESULT_OK
import kr.co.lion.basic_assignment.util.Constant.Companion.REMOVE_RESULT_OK
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity(), MainRvAdapter.ItemClick {
    private lateinit var binding: ActivityMainBinding
    private val memoList = ArrayList<DailyMemo>()

    private lateinit var inputMemoActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var showActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var modifyActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRv()
        initActivityProcess()
        initToolbar()
    }

    private fun initActivityProcess(){

        val inputMemoActivityContact = ActivityResultContracts.StartActivityForResult()
        inputMemoActivityLauncher = registerForActivityResult(inputMemoActivityContact){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        val newMemo = it.data?.getParcelableExtra("newMemo", DailyMemo::class.java)
                        addMemo(newMemo!!)
                    }else{
                        val newMemo = it.data?.getParcelableExtra<DailyMemo>("newMemo")
                        addMemo(newMemo!!)
                    }
                }
            }
        }

        val showActivityContact = ActivityResultContracts.StartActivityForResult()
        showActivityLauncher = registerForActivityResult(showActivityContact){

            when(it.resultCode){
                REMOVE_RESULT_OK -> {
                    if (it.data != null){
                        val timestamp = it.data?.getLongExtra("removeSelectedMemo", 0)
                        removeMemo(timestamp)
                    }else{
                        val timestamp = it.data?.getLongExtra("removeSelectedMemo", 0)
                        removeMemo(timestamp)
                    }
                }

                MODIFY_RESULT_OK ->{
                    if (it.data != null){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            val modifiedMemo = it.data?.getParcelableExtra("modifiedMemo", DailyMemo::class.java)
                            modifyMemo(modifiedMemo!!)
                        }else{
                            val modifiedMemo = it.data?.getParcelableExtra<DailyMemo>("modifiedMemo")
                            modifyMemo(modifiedMemo!!)
                        }
                    }
                }
            }
        }

        val modifyActivityContact = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher =  registerForActivityResult(modifyActivityContact){

        }
    }

    private fun addMemo(newMemo: DailyMemo){
        memoList.add(newMemo)
        binding.rv.adapter?.notifyItemInserted(memoList.size - 1)
    }

    private fun modifyMemo(modifyMemo: DailyMemo){
        val index = memoList.indexOfFirst { it.day == modifyMemo.day }
        memoList[index].title =  modifyMemo.title
        memoList[index].content =  modifyMemo.content

        binding.rv.adapter?.notifyItemChanged(index)
    }

    private fun removeMemo(timestamp: Long?){
        val index = memoList.indexOfFirst { it.day == timestamp }
        if (index != -1){
            memoList.removeAt(index)
            binding.rv.adapter?.notifyItemRemoved(index)
            binding.rv.adapter?.notifyItemRangeChanged(index, memoList.size)
        }
    }

    private fun initToolbar(){
        binding.toolbar.apply {
            inflateMenu(R.menu.main_menu)

            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.main_menu_item_add -> {
                        val intent = Intent(this@MainActivity, InputMemoActivity::class.java)
                        inputMemoActivityLauncher.launch(intent)
                        true
                    }
                    else -> true
                }
            }
        }
    }

    private fun initRv(){
        binding.apply {
            rv.apply {
                val adapter = MainRvAdapter(memoList)

                adapter.itemClick = this@MainActivity

                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@MainActivity)

                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    override fun onClick(v: View, position: Int) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("selectedMemo", memoList[position])
        showActivityLauncher.launch(intent)
    }
}