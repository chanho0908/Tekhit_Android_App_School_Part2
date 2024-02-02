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
                        setModifyMemo(modifyMemo!!)
                    }else{
                        modifyMemo = it.data?.getParcelableExtra<DailyMemo>("modifyMemo")
                        setModifyMemo(modifyMemo!!)
                    }
                }
            }
        }
    }
    private fun setModifyMemo(meno: DailyMemo){
        binding.apply {
            title.setText(meno.title)
            content.setText(meno.content)
        }
    }

    private fun initToolbar(){
        binding.toolbar.apply {
            inflateMenu(R.menu.show_menu)

            setNavigationOnClickListener {
                sendResponse()
            }

            setOnMenuItemClickListener {
                requestModify(it)
                true
            }
        }
    }

    private fun sendResponse(){
        binding.apply {
            val modifiedTitle = title.text.toString()
            val modifiedContent = content.text.toString()

            if ((selectedMemo?.title != modifiedTitle) || (selectedMemo?.title != modifiedContent)){
                val modifiedMemo = DailyMemo(
                    modifiedTitle,
                    selectedMemo?.day,
                    modifiedContent
                )

                val intent = Intent()
                intent.putExtra("modifiedMemo", modifiedMemo)
                setResult(MODIFY_RESULT_OK, intent)
                finish()
            }else{
                finish()
            }
        }
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