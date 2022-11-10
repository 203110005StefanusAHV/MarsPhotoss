package com.stefanus.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//memanggil ggambar dari internet menggunakan link
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * membuat Moshi objek yang akan digunakan Retrofit , pastikan untuk menambahkan adaptor Kotlin.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Gunakan pembuat Retrofit untuk membuat objek retrofit menggunakan konverter Moshi
 * dengan objek Moshi.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * antarmuka yang menampilkan method [getPhotos]
 */
interface MarsApiService {
    /**
     * Mengembalikan [List] dari [MarsPhoto] dan metode ini dapat dipanggil dari Coroutine.
     * Anotasi @GET menunjukkan bahwa titik akhir "photos" akan diminta dengan GET
     * method HTTP
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/**
 * Objek Api publik yang mengekspos layanan Retrofit dengan lazy-initialized
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
