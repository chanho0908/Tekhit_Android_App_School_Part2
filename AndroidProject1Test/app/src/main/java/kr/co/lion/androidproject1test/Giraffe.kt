package kr.co.lion.androidproject1test

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Giraffe : Animal(), Parcelable {

    // 목의 길이
    var neckLength = 0
    // 달리는 속도
    var runSpeed = 0
}