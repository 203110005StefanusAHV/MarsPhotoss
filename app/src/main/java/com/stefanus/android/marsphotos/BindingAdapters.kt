package com.stefanus.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stefanus.android.marsphotos.R
import com.stefanus.android.marsphotos.network.MarsPhoto
import com.stefanus.android.marsphotos.overview.MarsApiStatus
import com.stefanus.android.marsphotos.overview.PhotoGridAdapter

/**
 * memperbaharui data yang di tampilkan pada [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * menggunakan Library untuk memuat gambar menggunakan URL ke [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Data binding ini menampikan [MarsApiStatus] dari permintaan jaringan dalam tampilan gambar.
 * jika permintaan sedang memuat, layar akan menampilan loading_animation.
 * jika program eror makan akan menampikan gambar rusak.  ketikan permintaan berhasil makan
 * maka akan menyembunyikan gambar.
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
