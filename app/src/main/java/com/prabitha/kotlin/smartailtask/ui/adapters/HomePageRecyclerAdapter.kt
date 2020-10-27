package com.prabitha.kotlin.smartailtask.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabitha.kotlin.smartailtask.R
import com.prabitha.kotlin.smartailtask.models.HomeMenuModel
import com.prabitha.kotlin.smartailtask.ui.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.home_page_item.view.*


// adapter for the home page menu list
class HomePageRecyclerAdapter(private val items: ArrayList<HomeMenuModel>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<HomePageRecyclerAdapter.HomePageItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_page_item, parent, false)
        return HomePageItemHolder(view)
    }



    override fun onBindViewHolder(holder: HomePageItemHolder, position: Int) {

        holder.bind(items[position],viewModel)
    }

    override fun getItemCount(): Int = items.count()


    class HomePageItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(homeItem: HomeMenuModel, viewModel: HomeViewModel) {
            itemView.video_title.text= homeItem.title
            Glide.with(itemView.context).load(homeItem.iconLocation).into(itemView.video_thumbnail)
            itemView.tag=layoutPosition
            itemView.setOnClickListener {
                if(adapterPosition==0 ||  adapterPosition==5)
                viewModel.onMenuClicked(adapterPosition)
            }

        }
    }
}