package kr.co.lion.android_mini_project_1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Animal(var name: String?, var age: Int): Parcelable

class Lion(name: String, age: Int, var hairCnt:Int, var gender: String)
    : Animal(name, age)

class Tiger(name: String, age: Int, var stripeCount: Int, var weight: Int)
    : Animal(name, age)

class Giraffe(name: String, age: Int, var neckLength: Int, var runningSpeed: Int)
    : Animal(name, age)