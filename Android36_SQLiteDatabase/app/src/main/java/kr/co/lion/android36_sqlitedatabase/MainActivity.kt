package kr.co.lion.android36_sqlitedatabase

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android36_sqlitedatabase.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// 데이터 베이스 : 데이터들을 저장하고 관리하는 데이터 집합
// SQLite : 데이터 베이스를 관리하는 관계형 데이터 베이스 시스템(RDBMS)의 한 종류
// 테이블 : 데이터 베이스 내에서 데이터를 묶어서 관리는 요소
// 컬럼 : 테이블 내의 데이터 항목(컬럼, 열)
// 로우 : 테이블에 저장되어 있는 개체 하나에 대한 컬럼의 묶음(로우, 행)

// 안드로이드에서 SQLiteDatabase 사용
// 1. SQLiteOpenHelper 를 상속받은 클래스를 만들어준다(DBHelper.kt)

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

//        // SQLiteOpenHelper 객체 생성
//        val dbHelper = DBHelper(this, "Test.db", DBHelper.databaseVersion)
//
//        // 데이터 베이스에 접속
//        // 이때 베이터 베이스 파일을 찾게 된다. 파일이 있으면 데이터 베이스 파일을 열고
//        // 없으면 파일을 생성하고 데이터 베이스 파일을 열고 SQLiteOpenHelper 에 있는 onCreate 메서드를 호출한다.
//        // 만약 기존 데이터 베이스 파일 버전보다 더 높은 버전으로 설정하면 onUpgrade가 호출된다.
//        val sqLiteDatabase = dbHelper.writableDatabase
//
//        // 데이버 베이스를 닫아준다.
//        sqLiteDatabase.close()

        activityMainBinding.apply {
            // 저장(쿼리문)
            buttonInsert1.setOnClickListener {
                // 쿼리문
                // autoincrement를 설정한 컬럼은 제외한다.
                // insert into 테이블명 (컬럼명, 컬럼명, 컬럼명, ..) values (값, 값, 값, ...)
                // 값이 들어가는 부분은 ?로 설정해준다.

                val sql = """insert into TestTable
                    | (data1, data2, data3, data4)
                    | values (?, ?, ?, ?)
                """.trimMargin()

                // 현재 시간을 구해 년-월-일 양식의 문자열로 만들어준다.
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // ? 에 맵핑될 값
                // ? 에 맵핑될 값을 순서대로 작성해줘야 한다.
                val arg1 = arrayOf(100, 11.11, "문자열1", now)
                val arg2 = arrayOf(200, 22.22, "문자열2", now)

                // 데이터 베이스를 사용한다.
                val dbHelper = DBHelper(this@MainActivity, "Test.db", DBHelper.databaseVersion)

                // 쿼리문을 실행한다.
                // 첫 번째 - 쿼리문, 두 번째 - ? 에 바인딩될 값이 담긴 배열
                dbHelper.writableDatabase.execSQL(sql, arg1)
                dbHelper.writableDatabase.execSQL(sql, arg2)

                // 데이터 베이스를 닫아준다.
                dbHelper.close()

                textViewResult.text = "데이터가 저장되었습니다 (쿼리문)"
            }

            // 저장(라이브러리)
            buttonInsert2.setOnClickListener {
                // 현재 시간을 구해 년-월-일 양식의 문자열로 만들어준다.
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // 데이터를 담을 객체
                val cv1 = ContentValues()
                // ContentValues는 이름을 통해 데이터를 보관하는 객체이다.
                // 데이터를 저장할 때 사용하는 이름을 컬럼의 이름으로 지정해주면
                // 저장처리가 이루어진다.
                cv1.put("data1", 300)
                cv1.put("data2", 33.33)
                cv1.put("data3", "문자열3")
                cv1.put("data4", now)

                val cv2 = ContentValues()
                cv2.put("data1", 400)
                cv2.put("data2", 44.44)
                cv2.put("data3", "문자열4")
                cv2.put("data4", now)

                // 저장한다.
                val dbHelper = DBHelper(this@MainActivity, "Test.db", DBHelper.databaseVersion)

                // 첫 번째 : 데이터를 저장할 테이블의 이름
                // 두 번째 : null 값을 어떻게 처리할 것인가.. 그냥 null 지정해주세요....
                // 세 번째 : 저장할 데이터를 가지고 있는 ContentValues
                dbHelper.writableDatabase.insert("TestTable", null, cv1)
                dbHelper.writableDatabase.insert("TestTable", null, cv2)

                dbHelper.close()

                textViewResult.text = "데이터가 저장되었습니다 (라이브러리)"
            }


            buttonSelect1.setOnClickListener {

            }
        }


    }
}


