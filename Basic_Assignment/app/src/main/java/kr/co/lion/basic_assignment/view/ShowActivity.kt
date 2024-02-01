package kr.co.lion.basic_assignment.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.basic_assignment.R
import kr.co.lion.basic_assignment.databinding.ActivityShowBinding
import kr.co.lion.basic_assignment.model.DailyMemo
import kr.co.lion.basic_assignment.util.Constant.Companion.MODIFY_RESULT_OK
import kr.co.lion.basic_assignment.util.Constant.Companion.REMOVE_RESULT_OK
import kr.co.lion.basic_assignment.util.Constant.Companion.fromLongToDateString

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    private lateinit var modifyActivityLauncher: ActivityResultLauncher<Intent>

    private var selectedMemo: DailyMemo? = null
    private var modifyMemo: DailyMemo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initActivityProcess()
        initToolbar()
    }

    private fun initView(){
        selectedMemo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra("selectedMemo", DailyMemo::class.java)
        }else{
            intent.getParcelableExtra("selectedMemo")
        }

        binding.apply {
            title.setText(selectedMemo?.title)
            date.setText(fromLongToDateString(selectedMemo?.day))
            content.setText(selectedMemo?.content)
        }
    }

    private fun initActivityProcess(){
        val modifyActivityContact = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher =  registerForActivityResult(modifyActivityContact){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        modifyMemo = it.data?.getParcelableExtra("modifyMemo", DailyMemo::class.java)
                        Log.d("sdads", " initActivityProcess ${modifyMemo?.title}")
                        setModifyMemo(modifyMemo!!)
                    }else{
                        val modifyMemo = it.data?.getParcelableExtra<DailyMemo>("modifyMemo")
                        setModifyMemo(modifyMemo!!)
                    }
                }
            }
        }
    }
    private fun setModifyMemo(modifyMemo: DailyMemo){
        Log.d("sdads", "initActivityProcess ${modifyMemo?.title}")
        binding.apply {
            title.setText(modifyMemo.title)
            content.setText(modifyMemo.content)
        }
    }

    fun initToolbar(){
        Log.d("sdads", "initToolbar ${modifyMemo?.title}")
        binding.toolbar.apply {
            inflateMenu(R.menu.show_menu)

            setNavigationOnClickListener {
                val currentMemo = modifyMemo
                Log.d("sdads", "setNavigationOnClickListener ${modifyMemo?.title}")
                Log.d("sdads", "currentMemo ${currentMemo?.title}")
                sendResponse(currentMemo!!)
            }

            setOnMenuItemClickListener {
                requestModify(it)
                true
            }
        }
    }

    private fun sendResponse(memo: DailyMemo){

        Log.d("sdads", "sendResponse ${memo?.title}")
        val intent = Intent()
        intent.putExtra("modifyMemo", memo)
        setResult(MODIFY_RESULT_OK, intent)
        finish()

    }

    private fun requestModify(data: MenuItem){
        when(data.itemId){
            R.id.main_menu_item_modify -> {
                val intent = Intent(this@ShowActivity, ModifyActivity::class.java)
                intent.putExtra("selectedMemo", selectedMemo)
                modifyActivityLauncher.launch(intent)
            }

            R.id.main_menu_item_delete -> {
                showDialog()
            }
        }
    }

    private fun showDialog(){
        val builder = MaterialAlertDialogBuilder(this@ShowActivity).apply {
            setTitle("메모 삭제")
            setMessage("선택하신 메모를 삭제 하시겠습니까?")
            setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this@ShowActivity, MainActivity::class.java)
                intent.putExtra("removeSelectedMemo", selectedMemo?.day)
                setResult(REMOVE_RESULT_OK, intent)
                finish()
            }
            setNegativeButton("취소"){ dialogInterface: DialogInterface, i: Int ->

            }
        }

        builder.show()
    }
}