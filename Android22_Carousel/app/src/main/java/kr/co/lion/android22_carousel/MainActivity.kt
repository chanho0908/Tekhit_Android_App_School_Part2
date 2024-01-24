package kr.co.lion.android22_carousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.carousel.MultiBrowseCarouselStrategy
import kr.co.lion.android22_carousel.databinding.ActivityMainBinding
import kr.co.lion.android22_carousel.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // ReyclerView 구성을 위한 이미지들
    val imageRes = arrayOf(
        R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
        R.drawable.image_7, R.drawable.image_8, R.drawable.image_9,
        R.drawable.image_10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // RecyclerView 셋팅
            rv.apply {
                // 어뎁터
                adapter = RecyclerViewAdapter()
                // 레이아웃 매니저
                //layoutManager = CarouselLayoutManager()
                //layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                //layoutManager = CarouselLayoutManager(HeroCarouselStrategy())

                layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(val rowBinding: RowBinding): RecyclerView.ViewHolder(binding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            return ViewHolderClass(RowBinding.inflate(layoutInflater))
        }

        override fun getItemCount() = imageRes.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.carouselImageView.setImageResource(imageRes[position])
        }


    }
}