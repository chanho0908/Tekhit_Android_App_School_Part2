package kr.co.lion.androidproject1test

import kr.co.lion.android_mini_project_1.Util.AnimalType
import kr.co.lion.android_mini_project_1.Util.LION_GENDER

open class Animal() {

    // 동물 타입
    var type = AnimalType.ANIMAL_TYPE_LION
    // 이름
    var name = ""
    // 나이
    var age = 0
}

class Giraffe : Animal(){

    // 목의 길이
    var neckLength = 0
    // 달리는 속도
    var runSpeed = 0
}

class Lion(s: String, i: Int, i1: Int, s1: String) : Animal() {

    // 털의 개수
    var hairCount = 0
    // 성별
    var gender = LION_GENDER.LION_GENDER1
}

class Tiger : Animal(){
    // 줄무늬 개수
    var lineCount = 0
    // 몸무게
    var weight = 0
}