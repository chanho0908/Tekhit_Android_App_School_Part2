package kr.co.lion.basic_assignment.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kr.co.lion.basic_assignment.R
import kr.co.lion.basic_assignment.databinding.ActivityModifyBinding
import kr.co.lion.basic_assignment.model.DailyMemo
import kr.co.lion.basic_assignment.util.Constant
import kr.co.lion.basic_assignment.util.Constant.Companion.showSoftInput

class ModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyBinding

    private var selectedMemo: DailyMemo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initToolbar()
    }

    private fun initView(){
        binding.apply {

            showSoftInput(this@ModifyActivity, title)

            selectedMemo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("selectedMemo", DailyMemo::class.java)
            }else{
                intent.getParcelableExtra("selectedMemo")
            }

            title.setText(selectedMemo?.title)
            content.setText(selectedMemo?.content)

            title.addTextChangedListener {
                if (it.toString().isNotEmpty()) Constant.releaseWarningBox(
                    this@ModifyActivity,
                    titleLayout
                )
            }

            content.addTextChangedListener {
                if (it.toString().isNotEmpty()) Constant.releaseWarningBox(
                    this@ModifyActivity,
                    contentLayout
                )
            }
        }


    }

    private fun initToolbar(){
        binding.toolbar.apply {
            setNavigationOnClickListener { finish() }

            inflateMenu(R.menu.modify_menu)

            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.main_menu_item_submit -> {

                        val title = binding.title.text.toString()
                        val content = binding.content.text.toString()

                        if (title.isEmpty()) {
                            Constant.setWarningBox(
                                this@ModifyActivity,
                                binding.titleLayout,
                                ContextCompat.getString(this@ModifyActivity, R.string.plz_input_title)
                            )
                        }

                        if (content.isEmpty()) {
                            Constant.setWarningBox(
                                this@ModifyActivity,
                                binding.contentLayout,
                                ContextCompat.getString(this@ModifyActivity, R.string.plz_input_content)
                            )
                        }

                        if (title.isNotEmpty() && content.isNotEmpty())
                        {
                            sendResponse(title, content)
                        }

                        true
                    }
                    else -> true
                }
            }
        }
    }

    private fun sendResponse(title: String, content: String){
        binding.apply {

            val modifyMemo = DailyMemo(title, selectedMemo?.day, content)
            val intent = Intent()

            intent.putExtra("modifyMemo", modifyMemo)
            setResult(RESULT_OK, intent)
            finish()

        }
    }
}