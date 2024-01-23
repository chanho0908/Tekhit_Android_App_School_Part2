package kr.co.lion.ex07

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(private val oldList: ArrayList<DataModel>, private val newList: ArrayList<DataModel>): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id == newList[newItemPosition].id)
                && (oldList[oldItemPosition].name == newList[newItemPosition].name)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}