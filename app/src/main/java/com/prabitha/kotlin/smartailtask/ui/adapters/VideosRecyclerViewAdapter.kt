package com.prabitha.kotlin.smartailtask.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabitha.kotlin.smartailtask.R
import com.prabitha.kotlin.smartailtask.models.VideoModel
import kotlinx.android.synthetic.main.home_page_item.view.video_thumbnail
import kotlinx.android.synthetic.main.home_page_item.view.video_title
import kotlinx.android.synthetic.main.video_list_item.view.*

/*
* adapter for the videos page list
* */
class VideosRecyclerViewAdapter(private val items: ArrayList<VideoModel>) :
    RecyclerView.Adapter<VideosRecyclerViewAdapter.VideosItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return VideosItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideosItemViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()


    class VideosItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(videoItem: VideoModel) {
            itemView.video_title.text = videoItem.title
            itemView.video_subtitle.text = videoItem.subtitle
            Glide.with(itemView.context).load(R.drawable.bg_video).into(itemView.video_thumbnail)
        }
    }
}