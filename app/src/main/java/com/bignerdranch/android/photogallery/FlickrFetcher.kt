package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.PhotoDeserializer
import com.bignerdranch.android.photogallery.api.PhotoResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetcher"

class FlickrFetcher {
    private val flickrApi: FlickrApi

    init{
        /**
         * Chapter 24 challenge 1
         */
        val customDeserializer = GsonBuilder().registerTypeAdapter(PhotoResponse::class.java,
        PhotoDeserializer()).create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            /**
             * Chapter 24 challenge 1, Add customDeserializer as param
             */
            .addConverterFactory(GsonConverterFactory.create(customDeserializer))
            .build()

         flickrApi = retrofit.create(FlickrApi::class.java)
    }

    /**
     * Chapter 24 challenge 1, go through the rest of the code replacing FlickrResponse with
     * PhotoReponse where ever you see it
     */
    fun fetchPhotos(): LiveData<List<GalleryItem>>{
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<PhotoResponse> = flickrApi.fetchPhotos()

        flickrRequest.enqueue(object: Callback<PhotoResponse>{

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable){
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>){
                Log.d(TAG, "Response received")
                /**
                 * Change the response body to a PhotoResponse
                 * Chapter 24 challenge 1
                 */
                val photoResponse: PhotoResponse? = response.body()
                //val photoResponse: PhotoResponse? = flickrResponse?.photos
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems
                    ?: mutableListOf()
                galleryItems = galleryItems.filterNot{
                    it.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }
        })

        return responseLiveData
    }
}