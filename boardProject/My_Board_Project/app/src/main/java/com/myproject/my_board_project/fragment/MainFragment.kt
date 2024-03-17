package com.myproject.my_board_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.myproject.my_board_project.ContentActivity
import com.myproject.my_board_project.MainRecyclerViewAdapter
import com.myproject.my_board_project.R
import com.myproject.my_board_project.SearchRecyclerViewAdapter
import com.myproject.my_board_project.databinding.FragmentMainBinding
import kr.co.lion.androidproject4boardapp.ContentFragmentName

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        contentActivity = activity as ContentActivity

        settingToolbar()
        settingSearchBar()
        settingRecyclerViewMain()
        settingRecyclerViewMainSearch()

        return binding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        with(binding) {
            toolbarMain.apply {
                // 타이틀
                title = "전체 게시판"
                // 네비게이션
                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    // Drawer 메뉴가 나타나게 한다.
                    contentActivity.binding.drawerLayoutContent.open()
                }
            }
        }
    }

    // SearchView 설정
    fun settingSearchBar(){
        with(binding) {
            searchBarMain.apply {
                // SearchBar에 보여줄 메시지
                hint = "여기를 눌러 검색해주세요"
                // 메뉴
                inflateMenu(R.menu.menu_main_search_menu)
                setOnMenuItemClickListener {
                    // 글 작성 화면이 나타나게 한다.
                    contentActivity.replaceFragment(ContentFragmentName.ADD_CONTENT_FRAGMENT, true, true, null)
                    true
                }
            }

            searchViewMain.apply {
                // SearchView에 보여줄 메시지
                hint = "검색어를 입력해주세요"
            }
        }
    }

    // 메인 화면의 RecyclerView 설정
    fun settingRecyclerViewMain(){
        with(binding) {
            recyclerViewMain.apply {
                // 어뎁터
                adapter = MainRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 검색 화면의 RecyclerView를 구성하는 메서드
    fun settingRecyclerViewMainSearch(){
        with(binding) {
            recyclerViewMainSearch.apply {
                // 어뎁터
                adapter = SearchRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}