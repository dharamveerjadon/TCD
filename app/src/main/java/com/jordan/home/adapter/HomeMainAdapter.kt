package com.jordan.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordan.R
import com.jordan.home.model.NewsData

class HomeMainAdapter (val rowData: List<NewsData>, var context: Context) : RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout_main_home, parent, false)
        return ViewHolder(v,context)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HomeMainAdapter.ViewHolder, position: Int) {
        holder.bindItems(rowData[position],)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return rowData.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View,  var context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(rowData: NewsData) {
            val title = itemView.findViewById(R.id.title) as TextView
            title.text = rowData.title

            val description = itemView.findViewById(R.id.description) as TextView
            description.text = rowData.description

            val content = itemView.findViewById(R.id.content) as TextView
            content.text = rowData.content

            val authorName = itemView.findViewById(R.id.author_name) as TextView
            authorName.text = rowData.author

            val imageView = itemView.findViewById(R.id.banner) as ImageView
            Glide
                .with(context)
                .load(rowData.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.ic_smile)
                .into(imageView)
        }
    }
}