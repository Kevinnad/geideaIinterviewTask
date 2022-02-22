package com.example.interviewtask.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.interviewtask.data.model.DataModel
import com.example.interviewtask.databinding.DetailItemBinding

class MainViewHolder(
    val movieItemBinding: DetailItemBinding,
    val mainSubmitListener: MainAdapter.AdapterClickListener
) : RecyclerView.ViewHolder(movieItemBinding.root) {

    fun bind(item: DataModel?) {

        if (item != null) {
            movieItemBinding.dataModel = item

            movieItemBinding.root.setOnClickListener {
                mainSubmitListener.onItemClicked(item)
            }
        }

    }


}