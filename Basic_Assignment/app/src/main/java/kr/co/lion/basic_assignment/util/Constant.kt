package kr.co.lion.basic_assignment.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.basic_assignment.R
import java.text.SimpleDateFormat
import java.util.Locale

class Constant {
    companion object{

        const val REMOVE_RESULT_OK = 1001
        const val MODIFY_RESULT_OK = 1002

        fun setWarningBox(context: Context, v: TextInputLayout, msg: String) {
            v.helperText = msg
            v.setHelperTextColor(
                ContextCompat.getColorStateList(
                    context,
                    R.color.red
                )
            )
            v.boxStrokeColor = ContextCompat.getColor(context, R.color.red)
        }

        fun releaseWarningBox(context: Context, v: TextInputLayout) {
            v.helperText = ""
            v.boxStrokeColor = ContextCompat.getColor(context, R.color.black)
        }

        fun fromLongToDateString(now: Long?): String{
            val df = SimpleDateFormat("yyyy-MM-dd (E) HH:mm:ss", Locale.KOREAN)
            return df.format(now)
        }

        fun showSoftInput(context: Context, focusView: TextInputEditText) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(focusView, 0)
            }
        }
    }
}