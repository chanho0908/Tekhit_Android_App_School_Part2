package kr.co.lion.basic_assignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.basic_assignment.R
import kr.co.lion.basic_assignment.databinding.ActivityInputMemoBinding
import kr.co.lion.basic_assignment.model.DailyMemo
import kr.co.lion.basic_assignment.util.Constant.Companion.releaseWarningBox
import kr.co.lion.basic_assignment.util.Constant.Companion.setWarningBox
import kr.co.lion.basic_assignment.util.Constant.Companion.showSoftInput

class InputMemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initToolbar()
    }

    private fun initView(){
        binding.apply {
            title.requestFocus()
            showSoftInput(this@InputMemoActivity, title)

            title.addTextChangedListener {
                if (it.toString().isNotEmpty()) releaseWarningBox(this@InputMemoActivity, titleLayout)
            }

            content.addTextChangedListener {
                if (it.toString().isNotEmpty()) releaseWarningBox(this@InputMemoActivity, contentLayout)
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.apply {

            inflateMenu(R.menu.input_menu)

            setNavigationOnClickListener { finish() }

            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.input_menu_item_add ->{
                        val title = binding.title.text.toString()
                        val content = binding.content.text.toString()

                        if (title.isEmpty()) {
                            setWarningBox(
                                this@InputMemoActivity,
                                binding.titleLayout,
                                ContextCompat.getString(this@InputMemoActivity, R.string.plz_input_title)
                            )
                        }

                        if (content.isEmpty()) {
                            setWarningBox(
                                this@InputMemoActivity,
                                binding.contentLayout,
                                ContextCompat.getString(this@InputMemoActivity, R.string.plz_input_content)
                            )
                        }

                        if (title.isNotEmpty() && content.isNotEmpty())
                        {
                            responseData(title, content)
                        }
                    }
                }
                true
            }
        }
    }

    private fun responseData(title: String, content: String){
        val newMemo = DailyMemo(title, System.currentTimeMillis(), content)

        val intent = Intent()
        intent.putExtra("newMemo", newMemo)

        setResult(RESULT_OK, intent)
        finish()
    }
}