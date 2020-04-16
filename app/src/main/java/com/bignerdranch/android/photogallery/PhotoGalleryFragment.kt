package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_photo_gallery.*


private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment: Fragment() {

    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel

    private lateinit var photoRecyclerView: RecyclerView

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
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** chapter 24 challenge 2 **/
        /** Initialize photo Adapter here**/
        val mAdapter = PhotoAdapter()
        initSwipeToRefresh()
        photoGalleryViewModel.galleryItemList.observe(
            viewLifecycleOwner, Observer {
                Log.d("thelist", "$it")
                Log.d("thelistSize", "${it.size}")
                /** chapter 24 challenge 2
                 * MUST use submitList here for paging to work
                 */
                    mAdapter?.submitList(it)
                photoRecyclerView.adapter = mAdapter
                swiperefresh.isRefreshing = false


            }
        )


    }

    private fun initSwipeToRefresh() {
        swiperefresh.setOnRefreshListener {
            photoGalleryViewModel.refresh()
        }
    }

    private class PhotoHolder(itemTextView: TextView)
        : RecyclerView.ViewHolder(itemTextView){

        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    /** Chapter 24 challenge 2, implement PagedListAdapter **/
    /** it is important to remove the list from the constructor here **/
    private class PhotoAdapter
        : PagedListAdapter<GalleryItem,PhotoHolder>(object : DiffUtil.ItemCallback<GalleryItem>(){

        override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
            oldItem == newItem
    }


    ){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView = TextView(parent.context)
            return PhotoHolder(textView)
        }

        //override fun getItemCount(): Int = galleryItems.size

        /** Chapter 24 challenge 2
         * It is important to use getItem(position) here
         */
        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            //val galleryItem = galleryItems[position]
            val galleryItem = getItem(position)
            galleryItem?.title?.let { holder.bindTitle(it) }
        }




    }



    companion object{
        fun newInstance() = PhotoGalleryFragment()
    }
}