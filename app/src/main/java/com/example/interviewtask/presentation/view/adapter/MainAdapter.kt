package com.example.interviewtask.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.databinding.DetailItemBinding

class MainAdapter(val adapterClickListener: AdapterClickListener) : PagedListAdapter<DataModel, MainViewHolder>(MainComperator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),adapterClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface AdapterClickListener{

       fun onItemClicked(dataModel: DataModel)
    }

    object MainComperator : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel) =
            oldItem == newItem
    }
}