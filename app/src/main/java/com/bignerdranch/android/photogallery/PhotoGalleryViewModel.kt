package com.bignerdranch.android.photogallery


import androidx.lifecycle.*
import androidx.paging.toLiveData


class PhotoGalleryViewModel: ViewModel(){

    /** Chapter 24 Challenge 2 **/
    /** Don't forget to add paging dependencies to build.grad.e **/

    private val dataSourceFactory = GalleryItemDataSourceFactory()
    val galleryItemList = dataSourceFactory.toLiveData(pageSize = 100)





}