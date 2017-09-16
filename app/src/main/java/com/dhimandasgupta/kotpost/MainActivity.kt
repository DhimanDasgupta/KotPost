package com.dhimandasgupta.kotpost

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.coroutines.experimental.launch
import java.io.IOException

class MainActivity : AppCompatActivity(), PostClickListener {
    override fun onPostClicked(post: Post) {
        Toast.makeText(this, "Clicked ${post.title}", Toast.LENGTH_SHORT).show()
    }

    private lateinit var posts: RecyclerView
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postsLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        posts = findViewById(R.id.posts_list)
        postsAdapter = PostsAdapter(listener = this)
        postsLayoutManager = LinearLayoutManager(this)

        posts.apply {
            setHasFixedSize(true)
            layoutManager = postsLayoutManager
            adapter = postsAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        launch(Android) {
            try {
                val result = PostClient.fetchPosts()

                postsAdapter.setElements(result.await()) // will suspend until the call is finished
                postsAdapter.notifyDataSetChanged()
            } catch (exception: IOException){
                Toast.makeText(this@MainActivity, "Phone not connected or service down", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
