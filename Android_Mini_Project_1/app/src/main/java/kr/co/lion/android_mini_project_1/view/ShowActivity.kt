package kr.co.lion.android_mini_project_1.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android_mini_project_1.R
import kr.co.lion.android_mini_project_1.databinding.ActivityShowBinding
import kr.co.lion.android_mini_project_1.model.Animal
import kr.co.lion.android_mini_project_1.model.Giraffe
import kr.co.lion.android_mini_project_1.model.Lion
import kr.co.lion.android_mini_project_1.model.Tiger


class ShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowBinding

    // Activity 런처
    lateinit var modifyActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLauncher()
        setToolbar()
        setView()
    }

    // 런처 설정
    fun setLauncher(){
        // ModifyActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1){

        }
    }

    // 툴바 설정
    fun setToolbar(){
        binding.apply {
            toolbarShow.apply {
                // 타이틀
                title = "동물 정보"
                // back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_show)
                setOnMenuItemClickListener {
                    // 사용자가 선택한 메뉴 항목의 id로 분기한다.
                    when(it.itemId){
                        // 수정
                        R.id.menu_item_show_modify -> {
                            // ModifyActivity 실행
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)
                            modifyActivityLauncher.launch(modifyIntent)
                        }
                        // 삭제
                        R.id.menu_item_show_delete -> {

                        }
                    }

                    true
                }
            }
        }
    }

    // 뷰 설정
    fun setView(){
        val animalInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra("selectedAnimal", Animal::class.java)!!
        }else{
            intent.getParcelableExtra("selectedAnimal")!!
        }
        Log.d("sadsadas", "${animalInfo.name}" )
        binding.apply {
            // TextView
            textViewShowInfo.apply {
                if (animalInfo is Lion) {
                    Log.d("sadsadas", "사자임" )
                    val lion = animalInfo as Lion
                    Log.d("sadsadas", "$lion" )
                    text.apply {
                        append("이름 : ${lion.name}\n")
                        append("나이 : ${lion.age}\n")
                        append("털의 개수 : ${lion.hairCnt}\n") // 수정된 부분
                        append("성별 : ${lion.gender}")
                    }
                } else if (animalInfo is Tiger) {
                    val tiger = animalInfo as Tiger
                    text.apply {
                        append("이름 : ${tiger.name}\n")
                        append("나이 : ${tiger.age}\n")
                        append("줄무니 개수 : ${tiger.stripeCount}\n")
                        append("몸무게 : ${tiger.weight}")
                    }
                } else if (animalInfo is Giraffe) {
                    val giraffe = animalInfo as Giraffe
                    text.apply {
                        append("이름 : ${giraffe.name}\n")
                        append("나이 : ${giraffe.age}\n")
                        append("목의 길이 : ${giraffe.neckLength}\n")
                        append("달리기 속도 : ${giraffe.runningSpeed}")
                    }
                }
            }
        }
    }

    private fun setAnimalInfoText(){
        binding.textViewShowInfo.apply {

        }

    }
}