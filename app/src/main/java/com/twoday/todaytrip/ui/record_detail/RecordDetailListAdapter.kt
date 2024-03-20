package com.twoday.todaytrip.ui.record_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twoday.todaytrip.R
import com.twoday.todaytrip.databinding.ItemSavePhotoListBinding
import com.twoday.todaytrip.ui.place_list.adapter.OnTourItemAddClickListener
import com.twoday.todaytrip.ui.save_photo.SavePhotoData

class RecordDetailListAdapter() : ListAdapter<SavePhotoData, RecordDetailListAdapter.Holder>(
    TourItemDiffCallback()
) {

    class TourItemDiffCallback : DiffUtil.ItemCallback<SavePhotoData>() {
        override fun areItemsTheSame(oldItem: SavePhotoData, newItem: SavePhotoData): Boolean {
            return oldItem.imageUriList == newItem.imageUriList
        }
        override fun areContentsTheSame(oldItem: SavePhotoData, newItem: SavePhotoData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemSavePhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.position.text = (position+1).toString()
        holder.run {
            bind(getItem(position))
        }
    }

    inner class Holder(val binding: ItemSavePhotoListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.ivSavePhotoImage
        private val title = binding.tvSavePhotoRoadText
        private val address = binding.tvSavePhotoAddress
        val position = binding.tvSavePhotoPocketNumber
        fun bind(item: SavePhotoData) {
            title.text = item.tourItem.getTitle()
            address.text = item.tourItem.getAddress()
            if (!item.imageUriList.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(item.imageUriList[0])
                    .placeholder(R.drawable.img_default)
                    .into(image)
            }else {
                Glide.with(itemView.context)
                    .load(R.drawable.img_default)
                    .placeholder(R.drawable.img_default)
                    .into(image)
            }
        }
    }
}