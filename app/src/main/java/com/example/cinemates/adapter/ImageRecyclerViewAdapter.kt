package com.example.cinemates.adapter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemates.adapter.ImageRecyclerViewAdapter.ImageViewHolder
import com.example.cinemates.databinding.ListItemBackdropBinding
import com.example.cinemates.databinding.ListItemPosterBinding
import com.example.cinemates.model.data.ImagesResponse
import com.example.cinemates.util.DialogFactory
import com.example.cinemates.util.IMAGE_BASE_URL
import com.example.cinemates.util.help
import java.io.File

/**
 * @author Antonio Di Nuzzo
 * Created 23/06/2022 at 17:23
 */
class ImageRecyclerViewAdapter(private val mContext: Context) :
    ListAdapter<ImagesResponse.Image, ImageViewHolder>(asyncDiffConfig) {

    private val dataList: MutableList<ImagesResponse.Image> = ArrayList()

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<ImagesResponse.Image>() {
            override fun areItemsTheSame(oldItem: ImagesResponse.Image, newItem: ImagesResponse.Image): Boolean =
                oldItem.file_path == newItem.file_path

            override fun areContentsTheSame(oldItem: ImagesResponse.Image, newItem: ImagesResponse.Image): Boolean =
                areItemsTheSame(oldItem, newItem)
        }

        private val asyncDiffConfig = AsyncDifferConfig.Builder(diffCallback).build()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return if (viewType == ImagesResponse.Image.POSTER) {
            ImageViewHolder(parent help ListItemPosterBinding::inflate)
        } else {
            ImageViewHolder(parent help ListItemBackdropBinding::inflate)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItems(dataList: List<ImagesResponse.Image>?) {
        this.dataList.addAll(dataList!!)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val image = dataList[position]
        return image.imageType
    }

    private fun downloadImageNew(filename: String?, downloadUrlOfImage: String) {
        DialogFactory.createSimpleOkCancelDialog(mContext,
            "Want to download this image?",
            "Download",
            "Cancel",
            { _, _ ->
                try {
                    val dm = mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val downloadUri = Uri.parse(downloadUrlOfImage)
                    val request = DownloadManager.Request(downloadUri)
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle(filename)
                        .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_PICTURES,
                            File.separator + filename + ".jpg"
                        )
                    dm.enqueue(request)
                    Toast.makeText(mContext, "Image download started.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(mContext, "Image download failed.", Toast.LENGTH_SHORT).show()
                }
            }) { dialogInterface, i -> dialogInterface.dismiss() }.show()
    }

    class ImageViewHolder : RecyclerView.ViewHolder {
        var bindingPoster: ListItemPosterBinding? = null
        var bindingBackdrop: ListItemBackdropBinding? = null

        constructor(binding: ListItemPosterBinding) : super(binding.root) {
            bindingPoster = binding
        }

        constructor(binding: ListItemBackdropBinding) : super(binding.root) {
            bindingBackdrop = binding
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = dataList[position]
        if (getItemViewType(position) == ImagesResponse.Image.POSTER) {
            holder.bindingPoster!!.image = image
            holder.bindingPoster!!.executePendingBindings()
            holder.bindingPoster!!.root.setOnClickListener {
                downloadImageNew(
                    "${image.width}${image.height}",
                    "$IMAGE_BASE_URL${image.file_path}"
                )
            }
        } else {
            holder.bindingBackdrop!!.image = image
            holder.bindingBackdrop!!.executePendingBindings()
            holder.bindingBackdrop!!.root.setOnClickListener {
                downloadImageNew(
                    null,
                    "$IMAGE_BASE_URL${image.file_path}"
                )
            }
        }    }
}