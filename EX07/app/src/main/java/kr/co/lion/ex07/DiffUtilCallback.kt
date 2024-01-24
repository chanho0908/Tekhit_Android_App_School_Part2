package kr.co.lion.ex07

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(private val oldList: ArrayList<DataModel>, private val newList: ArrayList<DataModel>): DiffUtil.Callback() {
    // 기존 List의 size
    override fun getOldListSize() = oldList.size

    // 데이터가 변경된 List의 size
    override fun getNewListSize() = newList.size

    // 기존 List와 새로운 List가 같은 객체인가?
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id == newList[newItemPosition].id)
                && (oldList[oldItemPosition].name == newList[newItemPosition].name)
    }

    // 두 List의 데이터가 같은지 반환
    // areItemsTheSame이 true일 때만 호출
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}