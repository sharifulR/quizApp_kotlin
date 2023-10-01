package com.wbsoft.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wbsoft.quiz.adapter.CategoryAdapter
import com.wbsoft.quiz.databinding.FragmentHomeBinding
import com.wbsoft.quiz.model.CategoryModel


class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    //private val catList List<CategoryModel()> =Array
    val categoryList= mutableListOf<CategoryModel>()

    //private val catAdapter: CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catAdapter=CategoryAdapter()
        binding.categoryRecyclerView.layoutManager=
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.categoryRecyclerView.adapter=catAdapter
        catAdapter.submitList(categoryList)

        loadCategory()

    }

    private fun loadCategory() {
        categoryList.add(CategoryModel("1","GK","10"))
        categoryList.add(CategoryModel("2","History","5"))
        categoryList.add(CategoryModel("3","English","15"))
        categoryList.add(CategoryModel("4","Math","10"))
        categoryList.add(CategoryModel("5","Science","20"))
        categoryList.add(CategoryModel("6","Bangla","15"))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}