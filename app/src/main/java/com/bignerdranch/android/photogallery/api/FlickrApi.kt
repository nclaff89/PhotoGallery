package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/** Chapter 24 challenge 2 **/
/** add a query param for "page" so we can get pages at a time returned */

interface FlickrApi{
    /** chapter 24 challenge 2**/
    /** adding a query for specific page numbers **/
    @GET("services/rest/?method=flickr.interestingness.getList" +
    "&api_key=248dd0fcb0001664a108baf8fa93af54" +
    "&format=json" +
    "&nojsoncallback=1" +
    "&extras=url_s")
    fun fetchPhotos(@Query("per_page")perPage: Int, @Query("page")pageNum: Int): Call<FlickrResponse>
    //fun fetchPhotos(): Call<FlickrResponse>
}