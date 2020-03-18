package com.bignerdranch.android.photogallery.api


import android.util.Log
import com.bignerdranch.android.photogallery.GalleryItem
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * CHAPTER 24 CHALLENGE 1
 */
private const val TAG = "PhotoDeserializer"
class PhotoDeserializer: JsonDeserializer<PhotoResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {

        if(context == null){
            throw Exception()
        }else {

            val root = json?.asJsonObject!!.get("photos").asJsonObject
            val photoResponse =  Gson().fromJson(root, PhotoResponse::class.java)
            Log.d(TAG, "$photoResponse")


            return photoResponse
        }

    }
}