package kr.co.lion.android32_service

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kr.co.lion.android32_service.databinding.ActivityMainBinding

// 그럼 서비스는 일반 클래스에서 객체를 생성해서 접근 하는 것처럼 접근하는게 불가능한건가요 ?
// => 서비스는 OS가 생성하는 컴포넌트이다. 그래서 그냥 클래스 생성하듯이 하면 서비스가 아니라 그냥 클래스다

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 서비스 가동을 위해 사용할 Intent
    lateinit var serviceIntent: Intent

    // 서비스 객체의 주소값을 담을 프로퍼티
    var testService: TestService? = null

    // 서비스 접속을 관리하는 매니저
    lateinit var serviceConnectionClass: ServiceConnectionClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            button.setOnClickListener {
                // 현재 서비스가 실행중인지 파악한다.
                val chk = isServiceRunning("kr.co.lion.android32_service.TestService")
                // 서비스를 실행하기 위한 Intent 생성
                serviceIntent = Intent(this@MainActivity, TestService::class.java)

                // 서비스가 가동중이 아니라면
                if(!chk){
                    // 서비스를 가동한다.
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        startForegroundService(serviceIntent)
                    } else {
                        startService(serviceIntent)
                    }
                }

                // 서비스 접속에 성공하면 Service가 가지고 있는 onBind 메서드가 호출된다.
                //     override fun onBind(intent: Intent): IBinder {
                //        return binder
                //    }

                // Service가 가지고 있는 onBind 메서드에서 반환하는 객체를 OS가 받아둔다.

                // Activity에서 서비스에 접속했을 때 지정한 ServiceConnection 객체에 접근한다.
                // 아래의 두 번째 매개변수
                // bindService(serviceIntent, serviceConnectionClass, BIND_AUTO_CREATE)

                // OS가 ServiceConnection이 가지고 있는 onServiceConnected 메서드를 호출한다.
                // 이때, 두 번째 매개변수에 Service가 전달한 객체를 담아준다.
                // override fun onServiceConnected(name: ComponentName?, service: IBinder?)

                // onServiceConnected 메서드에서 Binder 객체를 통해 서비스 객체의 주소 값을 받아서
                // 서비스 객체에 접근할 수 있다.
                //    val binder = service as TestService.LocalBinder
                //    testService = binder.getService()

                // 서비스에 접속한다.
                serviceConnectionClass = ServiceConnectionClass()
                // BIND_AUTO_CREATE : 서비스가 가동 중이 아닐때 서비스를 가동시키라는 옵션. 현재는 동작하지 않는다.
                bindService(serviceIntent, serviceConnectionClass, BIND_AUTO_CREATE)
            }

            button2.setOnClickListener {
                // 실행중인 서비스를 중단시킨다.
                if(::serviceIntent.isInitialized){
                    stopService(serviceIntent)
                }
            }
        }
    }

    // 서비스가 가동중인지 확인하는 메서드
    fun isServiceRunning(name:String) : Boolean{
        // 서비스 관리자를 추출한다.
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        // 현재 실행 중인 서비스들을 가져온다.
        val serviceList = activityManager.getRunningServices(Int.MAX_VALUE)
        // 가져온 서비스의 수 만큼 반복한다.
        serviceList.forEach {
            // 현재 서비스의 이름이 동일한지 확인한다.
            if(it.service.className == name){
                return true
            }
        }
        return false
    }

    // 서비스에 접속을 관리하는 클래스
    inner class ServiceConnectionClass : ServiceConnection{
        // 서비스에 접속이 성공하면 호출되는 메서드
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TestService.LocalBinder
            // 서비스 객체를 추출한다.
            testService = binder.getService()
        }
        // 서비스 접속이 해제되면 호출되는 메서드
        override fun onServiceDisconnected(name: ComponentName?) {
            testService = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 서비스가 접속 중이라면 접속을 해제한다.
        if(::serviceConnectionClass.isInitialized){
            unbindService(serviceConnectionClass)
        }
    }
}