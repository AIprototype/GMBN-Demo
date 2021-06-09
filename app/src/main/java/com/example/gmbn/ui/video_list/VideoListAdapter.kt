package com.example.gmbn.ui.video_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.databinding.VideoListItemBinding
import com.example.gmbn.utils.ItemClickListener


class VideoListAdapter constructor(
    @NonNull diffCallback: DiffUtil.ItemCallback<Item?>?,
    var itemClickListener: ItemClickListener
) : ListAdapter<Item?, VideoListAdapter.MovieViewHolder?>(diffCallback!!) {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val videoListBinding = VideoListItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(videoListBinding)
    }

    override fun onBindViewHolder(@NonNull holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
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