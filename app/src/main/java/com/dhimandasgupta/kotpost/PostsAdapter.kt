package com.dhimandasgupta.kotpost;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by dhimandasgupta on 16/09/17.
 */

class PostsAdapter(var posts : List<Post> = ArrayList<Post>(),
                   val listener: PostClickListener) : RecyclerView.Adapter<PostViewHolder>(){

    fun setElements(elements : List<Post>){
        posts = elements
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.title.text = post.title
        holder.body.text = post.body
        holder.view.setOnClickListener { listener.onPostClicked(post) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view,
                view.findViewById(R.id.title),
                view.findViewById(R.id.body))
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}


interface PostClickListener{
    fun onPostClicked(post : Post)
}


class PostViewHolder(val view: View, val title : TextView, val body: TextView): RecyclerView.ViewHolder(view)
