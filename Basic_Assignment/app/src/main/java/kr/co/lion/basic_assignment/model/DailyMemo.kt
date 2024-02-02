package kr.co.lion.basic_assignment.model

import android.os.Parcel
import android.os.Parcelable

data class DailyMemo(
    var title: String?,
    val day: Long?,
    var content: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeValue(day)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DailyMemo> {
        override fun createFromParcel(parcel: Parcel): DailyMemo {
            return DailyMemo(parcel)
        }

        override fun newArray(size: Int): Array<DailyMemo?> {
            return arrayOfNulls(size)
        }
    }

}