package com.example.gmbn.ui.video_list

import androidx.recyclerview.widget.DiffUtil
import com.example.gmbn.data.network.models.response.Item

class VideoDiffCallBack : DiffUtil.ItemCallback<Item?>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id.equals(newItem.id)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}