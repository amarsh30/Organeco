package com.organeco

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.organeco.databinding.ItemFertilizerBinding
import java.util.*
import kotlin.collections.ArrayList

class BookmarkAdapter (private val listBookmark : ArrayList<Recommendation>) :
    RecyclerView.Adapter<BookmarkAdapter.UserViewHolder>()
{
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class UserViewHolder(var binding: ItemFertilizerBinding) : RecyclerView.ViewHolder(binding.root) {  //inner class UserViewHolder
        fun binding(user: Recommendation) {

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemFertilizerBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding(listBookmark[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(
                listBookmark[holder.adapterPosition]
            )
        }
    }

    override fun getItemCount(): Int = listBookmark.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Recommendation)
    }
}