package com.dhimandasgupta.kotpost

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by dhimandasgupta on 16/09/17.
 */
object PostClient {
    val client = OkHttpClient()

    fun fetchPosts(): Deferred<List<Post>> {
        return async(CommonPool) {
            delay(5000)
            val request = Request.Builder().url("https://jsonplaceholder.typicode.com/posts").build()
            val response = client.newCall(request).execute()
            val postsType = object : TypeToken<List<Post>>() {}.type
            Gson().fromJson<List<Post>>(response.body().string(), postsType)
        }
    }
}
