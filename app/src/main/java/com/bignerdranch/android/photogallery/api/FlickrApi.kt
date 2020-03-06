package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi{

    @GET("services/rest/?method=flickr.interestingness.getList" +
    "&api_key=248dd0fcb0001664a108baf8fa93af54" +
    "&format=json" +
    "&nojsoncallback=1" +
    "&extras=url_s")
    fun fetchPhotos(): Call<FlickrResponse>
}