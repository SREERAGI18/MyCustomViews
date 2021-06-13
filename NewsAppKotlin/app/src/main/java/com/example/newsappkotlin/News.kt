package com.example.newsappkotlin

//class News (title:String, imageUrl : String, description:String, source:String, pubDate:String) {
//
//    var title = if(title == null) "null" else title
//    var imageUrl = if(imageUrl == null) "null" else imageUrl
//    var description = if(description == null) "null" else description
//    var source = if(source == null) "null" else source
//    var pubDate = pubDate
//
//}

data class News(
        val title: String?,
        val link: String?,
        val creator: String?,
        val image_url: String?,
        val pubDate:String?
)