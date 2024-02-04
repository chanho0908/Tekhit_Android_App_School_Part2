package kr.co.lion.android_mini_project_1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Animal(val name: String?, val age: Int): Parcelable

class Lion(name: String, age: Int, val hairCnt:Int, val gender: String)
    : Animal(name, age)

class Tiger(name: String, age: Int, val stripeCount: Int, val weight: Double)
    : Animal(name, age)

class Giraffe(name: String, age: Int, val neckLength: Double, val runningSpeed: Double)
    : Animal(name, age)