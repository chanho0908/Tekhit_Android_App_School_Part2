package com.myproject.cloudbridge.adapter.rv

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.myproject.cloudbridge.databinding.StoreMenuItemBinding
import com.myproject.cloudbridge.model.store.StoreMenuRvModel
import com.myproject.cloudbridge.util.setHelperBoxBlack
import com.myproject.cloudbridge.util.setHelperTextRed

class MenuRvAdapter(private val context: Context,
                    private val menuList: ArrayList<StoreMenuRvModel>,
                    private val imgClickListener: (Int) -> Unit,
                    private val delButtonClickListener: (Int) -> Unit):
    RecyclerView.Adapter<MenuRvAdapter.MenuRvViewHolder>() {

    // 포커스 상태를 추적하기 위한 변수
    private var isProductNameFocused = false

    inner class MenuRvViewHolder(val binding: StoreMenuItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRvViewHolder {
        return MenuRvViewHolder(StoreMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int{
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuRvViewHolder, position: Int) {
        val binding = holder.binding

        binding.apply {
            menuImgView.apply {
                setOnClickListener{
                    imgClickListener.invoke(position)
                }
                if (menuList[position].imgBitmap != null){
                    setImageBitmap(menuList[position].imgBitmap)
                }
            }

            delButton.setOnClickListener{
                delButtonClickListener.invoke(position)
            }

            productNameEdit.addTextChangedListener {
                val input = it.toString()

                if (input.isEmpty()){
                    Log.d("dasds", "이름이 없음")
                    productNameLayout.helperText = "상품명을 입력해 주세요"
                    productNameLayout.boxStrokeColor = context.setHelperTextRed()

                }else {
                    Log.d("dasds", "이름이 있음")
                    productNameLayout.helperText = ""
                    productNameLayout.boxStrokeColor = context.setHelperBoxBlack()
                    menuList[position].productName = input
                }

            }

            productNameEdit.setOnFocusChangeListener { view, hasFocus ->
                val input = productNameEdit.text.toString()

                if (!hasFocus && input.isEmpty()) {
                    Log.d("dasds", "포커스를 잃음")
                    productNameLayout.helperText = "상품명을 입력해 주세요"
                    productNameLayout.boxStrokeColor = context.setHelperTextRed()
                }
            }

            productQuantityEdit.addTextChangedListener {
                val input = it.toString()

                if (input.isEmpty()){
                    productQuantityLayout.helperText = "수량을 입력해 주세요"
                    productQuantityLayout.boxStrokeColor = context.setHelperTextRed()
                }else {
                    productQuantityLayout.helperText = ""
                    productQuantityLayout.boxStrokeColor = context.setHelperBoxBlack()
                    menuList[position].productQuantity = it.toString().toInt()
                }

            }

            productIntroEdit.addTextChangedListener{
                menuList[position].productIntro = it.toString()
            }
        }

    }
    fun getRvListData(): ArrayList<StoreMenuRvModel> = menuList

}



