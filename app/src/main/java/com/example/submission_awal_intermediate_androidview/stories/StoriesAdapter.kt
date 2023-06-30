package com.example.submission_awal_intermediate_androidview.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission_awal_intermediate_androidview.databinding.ItemStoryBinding
import com.example.submission_awal_intermediate_androidview.model.Story

class StoriesAdapter: PagingDataAdapter<Story, StoriesAdapter.StoriesViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemCLickCallback?= null

    inner class StoriesViewHolder(private val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun getStory(story: Story) {
            binding.apply {
                Glide.with(itemView)
                    .load(story.photoUrl)
                    .centerCrop()
                    .into(imgStoriesThumbnail)
                tvStoriesDateUpload.text = DateFormatter.formatDate(story.createdAt, "Asia/Jakarta")
                tvStoriesTitle.text = story.name
                tvStoriesDescription.text = story.description

                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(story)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoriesAdapter.StoriesViewHolder {
        val data = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoriesViewHolder(data)
    }

    override fun onBindViewHolder(holder: StoriesAdapter.StoriesViewHolder, position: Int) {
        val story = getItem(position)
        if (story!=null) {
            holder.getStory(story)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemCLickCallback {
        fun onItemClicked(story: Story)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}