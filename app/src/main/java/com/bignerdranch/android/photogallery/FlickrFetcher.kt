package com.bignerdranch.android.photogallery

import android.app.Application
import android.util.Log
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.FlickrResponse
import com.bignerdranch.android.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetcher"

class FlickrFetcher() {
     val flickrApi: FlickrApi



    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         flickrApi = retrofit.create(FlickrApi::class.java)
    }

    /** Chapter 24 challenge 2 **/
//    fun fetchPhotos(){
//        //val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
//        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos()
//
//        flickrRequest.enqueue(object: Callback<FlickrResponse>{
//
//            override fun onFailure(call: Call<FlickrResponse>, t: Throwable){
//                Log.e(TAG, "Failed to fetch photos", t)
//            }
//
//            override fun onResponse(call: Call<FlickrResponse>, response: Response<FlickrResponse>){
//                Log.d(TAG, "Response received")
//                Log.d("Response", "${response.body()}")
//                val flickrResponse: FlickrResponse? = response.body()
//                val photoResponse: PhotoResponse? = flickrResponse?.photos
//                Log.d("Response", "$photoResponse")
//                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems
//                    ?: mutableListOf()
//                galleryItems = galleryItems.filterNot{
//                    it.url.isBlank()
//                }
//                //responseLiveData.value = galleryItems
//                Log.d("GalleryItems", "$galleryItems")
//                galleryItems.forEach {galleryItem ->
//                    galleryItemDao.insertGalleryItem(galleryItem)
//
//                }
//            }
//        })
////        val list = responseLiveData.value ?: mutableListOf()
////        Log.d("List", "$list")
//       // return responseLiveData
//    }
}