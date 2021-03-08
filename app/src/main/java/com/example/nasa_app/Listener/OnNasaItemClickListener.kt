package com.example.nasa_app.Listener

import com.example.nasa_app.Model.Photo

interface OnNasaItemClickListener {
    fun onClick(photo: Photo?)
}