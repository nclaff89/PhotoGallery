package com.bignerdranch.android.photogallery.api

/** Chapter 24 challenge 2 **/
/** Code snipped heavily borrowed from a Medium article */
class ApiService (

        private val flickrApi: FlickrApi
    ) {

        fun fetchPhotosSync( page: Int,
                            onPrepared: () -> Unit,
                            onSuccess: (FlickrResponse?) -> Unit,
                            onError: (String) -> Unit) {

            val request = flickrApi.fetchPhotos(page)
            onPrepared()
            ApiHelper().syncRequest(request, onSuccess, onError)
        }

        fun fetchPhotosAsync( page: Int,
                             onPrepared: () -> Unit,
                             onSuccess: (FlickrResponse?) -> Unit,
                             onError: (String) -> Unit) {

            val request = flickrApi.fetchPhotos(page)
            onPrepared()
            ApiHelper().asyncRequest(request, onSuccess, onError)
        }

}