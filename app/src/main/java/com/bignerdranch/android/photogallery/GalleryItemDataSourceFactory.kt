package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/** Chapter 24 challenge 2 **/
class GalleryItemDataSourceFactory(

) : DataSource.Factory<Int, GalleryItem>() {



    private val sourceLiveData = MutableLiveData<GalleryItemDataSource>()
    override fun create(): DataSource<Int, GalleryItem> {
        val latestSource = GalleryItemDataSource()
        Log.d("LatestSource", "$latestSource")
        sourceLiveData.postValue(latestSource)
        Log.d("SourceLiveData", "${sourceLiveData}")
        return latestSource
    }


}