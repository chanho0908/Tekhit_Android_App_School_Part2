package kr.co.naveropenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.naveropenapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 1. 반드시 관련 문서를 찾는다.
// - https://developers.naver.com/docs/serviceapi/search/news/news.md#%EB%89%B4%EC%8A%A4

// 2. API 키(혹은 접속시 필요한 개발자 구분값) 발급 방법을 확인해야 한다.
// - 애플리케이션을 등록하면 클라이언트 ID와 클라이언트 시크릿을 발급받을 수 있다.

// 3. 요청할 페이지의 주소를 확인해야 한다.
// - https://openapi.naver.com/v1/search/news.json

// 4. HTTP 요청 헤더로 전달할 데이터가 있는지 확인한다.
// - X-Naver-Client-Id : 발급 받은 클라이언트 ID
// - X-Naver-Client-Secret : 발급 받은 클라이언트 시크릿

// 5. 파라미터로 전달할 데이터가 있는지 확인한다.
// - query : 검색어(필수)
// - display : 한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
// - start : 검색 시작 위치(기본값: 1, 최댓값: 1000)
// - sort : 검색 결과 정렬 방법, sim: 정확도순으로 내림차순 정렬(기본값), date: 날짜순으로 내림차순 정렬

// 6. 요청 방식(GET or POST)을 확인한다.
// - GET

// 7. 서버가 전달해주는 데이터의 양식을 확인한다.
// 8. 오류 코드를 확인한다.
// 9. POSTMAN 을 이용해 요청 테스트를 한다.


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitInstance.getInstance()

        val clientId = "kXLip0OB6P7m4qGGKrpn"
        val clientSecret = "FAehAzDIdJ"
        val keyword ="선거"
        val display = 100

        with(binding){

        buttonRequest.setOnClickListener {
            val callNews = retrofit.getSearchNews(clientId, clientSecret, keyword, display)

            callNews.enqueue(object : Callback<ResultNews> {
                override fun onResponse(call: Call<ResultNews>, response: Response<ResultNews>) {
                    response.body()?.apply {
                        textViewResult.append("lastBuildDate : ${lastBuildDate}\n")
                        textViewResult.append("total : ${total}\n")
                        textViewResult.append("start : ${start}\n")
                        textViewResult.append("display : ${display}\n\n")

                        items.forEach {
                            textViewResult.append("title : ${it.title}\n")
                            textViewResult.append("originallink : ${it.originallink}\n")
                            textViewResult.append("link : ${it.link}\n")
                            textViewResult.append("description : ${it.description}\n")
                            textViewResult.append("pubDate : ${it.pubDate}\n\n")
                        }
                    }
                }

                override fun onFailure(call: Call<ResultNews>, error: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        }
    }
}