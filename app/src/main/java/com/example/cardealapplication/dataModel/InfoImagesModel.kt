package com.example.cardealapplication.dataModel

data class InfoImagesModel(
    var Image: List<Images>){
    data class Images(var image_url:String)
}
