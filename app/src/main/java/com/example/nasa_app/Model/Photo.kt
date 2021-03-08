package com.example.nasa_app.Model

data class Photo(@JvmField var ıd: Int, var sol: Int, @JvmField var camera: Camera,@JvmField  var img_src: String, var earth_date: String, var rover: Rover)