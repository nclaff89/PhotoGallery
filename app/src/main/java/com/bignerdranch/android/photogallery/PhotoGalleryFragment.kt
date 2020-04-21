package com.bignerdranch.android.photogallery

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.photogallery.api.FlickrApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment: Fragment() {

    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel

    private lateinit var photoRecyclerView: RecyclerView

    /** Chapter 24 challenge 3 */
    private lateinit var gridManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        /** chapter 24 challenge 3 */
        gridManager = GridLayoutManager(context, 3)
        photoRecyclerView.layoutManager = gridManager



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** chapter 24 challenge 3 **/
        photoRecyclerView.viewTreeObserver.addOnGlobalLayoutListener {
                var numberOfColumns = 1
                val viewWidth = photoRecyclerView.measuredWidth
                val singleElementWidth = 240
                if(viewWidth > singleElementWidth){
                    numberOfColumns = viewWidth / singleElementWidth
                }
                gridManager.spanCount = numberOfColumns
                photoRecyclerView.layoutManager = gridManager
                Log.d("Col", "$numberOfColumns")

        }


        photoGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner, Observer { galleryItems ->
                photoRecyclerView.adapter = PhotoAdapter(galleryItems)
            }
        )


    }

    private class PhotoHolder(itemTextView: TextView)
        : RecyclerView.ViewHolder(itemTextView){

        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class PhotoAdapter(private val galleryItems: List<GalleryItem>)
        :RecyclerView.Adapter<PhotoHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView = TextView(parent.context)
            //val textView = TextView(parent.context, R.id.textView)
            return PhotoHolder(textView)
        }

        override fun getItemCount(): Int = galleryItems.size

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindTitle(galleryItem.title)
        }
    }

    companion object{
        fun newInstance() = PhotoGalleryFragment()
    }
}