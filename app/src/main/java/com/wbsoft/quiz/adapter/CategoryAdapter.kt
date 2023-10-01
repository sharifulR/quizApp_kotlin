package com.wbsoft.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wbsoft.quiz.databinding.CategoryListItemBinding
import com.wbsoft.quiz.model.CategoryModel

class CategoryAdapter : ListAdapter<CategoryModel, CategoryAdapter.CategoryViewHolder>(CategoryDiffUtil()){

    class CategoryViewHolder(private val binding : CategoryListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryModel: CategoryModel){

            binding.category=categoryModel
        }

    }

    class CategoryDiffUtil :DiffUtil.ItemCallback<CategoryModel>(){
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.docId==newItem.docId
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding=CategoryListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val allCategory= getItem(position)
        allCategory?.let {
            holder.bind(it)
        }
    }
}


