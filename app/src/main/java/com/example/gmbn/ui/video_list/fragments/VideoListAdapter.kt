package com.example.gmbn.ui.video_list.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.databinding.VideoListItemBinding
import com.example.gmbn.utils.ItemClickListener


class VideoListAdapter constructor(
    @NonNull diffCallback: DiffUtil.ItemCallback<Item>,
    var itemClickListener: ItemClickListener
) : PagingDataAdapter<Item, RecyclerView.ViewHolder>(diffCallback) {

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        val videoListBinding = VideoListItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(videoListBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: VideoListItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: Item?) {
            binding.videoItemData = item
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.onClick(p0, bindingAdapterPosition, false)
        }
    }

}