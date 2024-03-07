package com.myproject.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.myproject.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 코루틴
// 동시에 작업을 하거나 오류가 발생할 가능성이 높은 코드를 처리하는데 사용한다.
// 쓰래드와 유사하지만 쓰래드의 단점을 보완하기 위해 만들어졌다.
// 1. 쓰래드 보다 메모리 사용량이 적어 작업의 처리가 더 빨리 끝난다.
// 2. 비동기적 처리(동시에 여러 작업을 수행)를 위해 사용하지만 동기적(순차 처리)으로 운영하기가 쉽다.
// 3. 중간에 중단하기기 쉽다.
// 4. 다른 루틴에서 발생시킨 데이터를 가져오는게 매우 쉽다.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var w1:Job
    private lateinit var w2: Job
    private lateinit var w3:Job
    private lateinit var mainWorking:Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            button1.setOnClickListener {
                // 여러 코루틴을 관리할 수 있는 객체
                // CoroutineScope : 발생 시키는 코루틴의 용도를 지정한다.
                // Main : 안드로이드의 MainThread가 처리한다
                // IO : 데이터 입출력 용, 별도의 스레드가 생성
                CoroutineScope(Dispatchers.Main).launch {
                    launch {
                        working1()
                    }
                    launch {
                        working2()
                    }
                    launch {
                        working3()
                    }
                }
            }

            button2.setOnClickListener {

                mainWorking = CoroutineScope(Dispatchers.Main).launch {
                    val job1 = launch {
                        working1()
                    }

                    // 첫 번째 코루틴이 끝날 때 까지 대기한다.
                    job1.join()

                    launch {
                        working2()
                    }
                    launch {
                        working3()
                    }
                }
            }

            button3.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    w1 = launch {
                        working1()
                    }

                    w2 = launch {
                        working2()
                    }
                    w3 = launch {
                        working3()
                    }
                }
            }

            button4.setOnClickListener {
//                w1.cancel()
//                w3.cancel()

                // 모든 코루틴의 수행을 중단
                mainWorking.cancel()
            }

            button5.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // 코루틴으로 운영하는 함수에서 반화는 값을 받으려면
                    // launch가 아닌 async 로 가동해야 한다.
                    val job1 = async {
                        working4()
                    }
                    val job2 = async {
                        working5()
                    }
                    val job3 = async {
                        working6()
                    }

                    // await : 코루틴으로 운영하는 함수가 반환하는 값을 받아올 수 있다.
                    // 코루틴이 관리하는 코드가 수행이 완료되면 return 부분은 수행하지 않고 대기 상태가 된다
                    // 이 때 await을 호출하면 return 부분이 수행되어 값을 반환하게 된다.
                    textView1.text = "job1 : ${job1.await()}"
                    textView2.text = "job2 : ${job2.await()}"
                    textView3.text = "job2 : ${job3.await()}"
                }
            }

            button6.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    // 코루틴 가동
                    // 코루틴으로 운영하는 함수에서 반화는 값을 받으려면
                    // launch가 아닌 async 로 가동해야 한다.
                    val job1 = async {
                        working4()
                    }

                    // await을 호출하면 코루틴의 작업이 끝날 때 까지 대기하고 있다가 반환하면
                    // 그 값을 받은 다음 다음으로 이어 나간다.
                    textView1.text = "job1 : ${job1.await()}"

                    val job2 = async {
                        working5()
                    }
                    textView2.text = "job2 : ${job2.await()}"

                    val job3 = async {
                        working6()
                    }

                    textView3.text = "job2 : ${job3.await()}"
                }
            }
        }


    }

    suspend fun working1(){
        for (x in 0..10){
            // 500ms 쉬었다 간다
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView1.text = "working1 : $now"
        }
    }

    suspend fun working2(){
        for (x in 0..10){
            // 500ms 쉬었다 간다
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView2.text = "working2 : $now"
        }
    }

    suspend fun working3(){
        for (x in 0..10){
            // 500ms 쉬었다 간다
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView3.text = "working3: $now"
        }
    }

    suspend fun working4(): Int{
        var a2 = 0

        for (x in 0..10){
            // 500ms 쉬었다 간다
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView1.text = "working3: $now"

            a2++
        }
        return a2
    }

    suspend fun working5():Int{

        var a2 = 0

        for(a1 in 0..10){
            // 500ms 쉬었다 간다(코루틴에서만 사용 가능하다)
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView2.text = "working5 : $now"

            a2++
        }

        return a2
    }

    suspend fun working6():Int{

        var a2 = 0

        for(a1 in 0..10){
            // 500ms 쉬었다 간다(코루틴에서만 사용 가능하다)
            delay(500)
            val now = System.currentTimeMillis()
            binding.textView3.text = "working6 : $now"

            a2++
        }

        return a2
    }
}