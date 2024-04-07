package kr.co.naveropenapi


// 서버가 전달하는 데이터를 담을 객체를 생성할 클래스
// 프로퍼티의 이름을 서버가 전달하는 데이터의 이름과 동일하게 해주면
// 자동으로 값이 들어온다.
// 정수, 실수, 문자열, 불리언 값들은 각 값에 타입에 맞는 프로퍼티로 정의해준다.
// { } : 클래스로 정의해준다.
// [ ] : List로 정의해준다.
data class ResultNews(
    var lastBuildDate:String = "",
    var total:Int = 0,
    var start:Int = 0,
    var display:Int = 0,
    var items:List<ItemClass>
)

data class ItemClass(
    var title:String = "",
    var originallink:String = "",
    var link:String = "",
    var description:String = "",
    var pubDate:String = ""
)