package kr.co.lion.ex09_activity_example.model

import android.os.Parcel
import android.os.Parcelable

data class Student(
    val name: String,
    val grade: String,
    val kor: String,
    val eng: String,
    val math: String
)
//): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//    // 객체를 다른 실행단위로 보낼때 호출되는 메서드
//    // 안드로이드 OS에서 첫 번째 매개변수로 parcel 객체가 전달된다.
//    // 이 객체는 다른 실행 단위로 전달되는 객체이다.
//    // parcel 객체는 객체가 가지고 있는 프로퍼티의 값을 저장해준다.
//    // 이 값은 다른 실행단위로 전달된다.
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeString(grade)
//        parcel.writeString(kor)
//        parcel.writeString(eng)
//        parcel.writeString(math)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    // 이 메서드는 parcel을 전달받은 새로운 실행단위에서 호출되는 메서드
//    // 이 메서드에서는 사용하고자 하는 객체를 생성한다.
//    // 매개 변수에는 parcel 객체가 전될단다.
//    // parcel 객체에 저장되어 있는 값들을 추출하여 새롭게 생성한 객체의
//    // 프로퍼티에 저장해주는 작업을 한다.
//    companion object CREATOR : Parcelable.Creator<Student> {
//        override fun createFromParcel(parcel: Parcel): Student {
//            return Student(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Student?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
