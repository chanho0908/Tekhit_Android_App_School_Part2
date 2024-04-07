package kr.co.naveropenapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// 서버에 요청하기 위해 필요한 데이터(주소, 헤더정도, 파라미터 등)을
// 설정하는 인터페이스이다.
// 이 인터페이스를 구현하고 요청에 필요한 메서드들이 구현되어 있는 클래스가 자동으로
// 만들어진다.
interface NaverAPI {
    // 요청 방식과 주소를 설정한다.
    // 주소는 기본 주소와 변경 주소로 나뉜다.
    // 여기에서 변경 주소를 설정한다.
    // GET 요청은  @GET, POST 요청은 @POST를 사용한다.
    @GET("news.json")
    // 헤더 정보와 파라미터 정보를 설정하는 메서드
    fun getSearchNews(
        // 헤더 정보
        @Header("X-Naver-Client-Id") clientId : String,
        @Header("X-Naver-Client-Secret") clientSecret:String,
        // 파라미터
        @Query("query") query:String,
        @Query("display") display:Int
    ): Call<ResultNews>
}