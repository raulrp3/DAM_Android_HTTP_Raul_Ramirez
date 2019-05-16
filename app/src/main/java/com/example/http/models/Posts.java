package com.example.http.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Posts {

    private List<Post> posts;

    public Posts(){this.posts = new ArrayList<>(); }

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Post oneFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Post.class);
    }

    public Posts manyFromJson(String json){
        Gson gson = new Gson();
        List<Post> posts = Arrays.asList(gson.fromJson(json, Post[].class));
        return new Posts(posts);
    }

    @Override
    public String toString() {
        return "Posts{" +
                "posts=" + posts +
                '}';
    }
}
