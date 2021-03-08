package com.example.nasa_app.Adapter

import android.content.Context
import com.example.nasa_app.Listener.OnNasaItemClickListener
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.nasa_app.R
import com.bumptech.glide.Glide
import android.widget.TextView
import com.example.nasa_app.Model.Photo
import java.util.ArrayList

class MainAdapter(var photos: ArrayList<Photo?>, var context: Context, var listener: OnNasaItemClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nasa_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = photos[position]!!.camera.name
        Glide.with(context).load(photos[position]!!.img_src).into(holder.imgphoto)
        holder.setOnClickListener(photos[position]!!.ıd)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imgphoto: ImageView
        var textView: TextView
         fun setOnClickListener(ids: Int) {
            itemView.tag = ids
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.onClick(photos[adapterPosition])
            //bir pop up açılıp pop up ta üstte resim alt kısımda ise
            //çekildiği tarih, araç adı, hangi kameradan çekildiği, aracın görev durumu, aracın fırlatma
            //tarihi ve iniş tarihi bilgileri yer almalıdır.
        }

        init {
            imgphoto = itemView.findViewById(R.id.rover_photos)
            textView = itemView.findViewById(R.id.postertitle)
        }
    }
}