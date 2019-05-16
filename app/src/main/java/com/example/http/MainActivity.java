package com.example.http;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.http.adapters.PostAdapter;
import com.example.http.asynctasks.HttpAsync;
import com.example.http.interfaces.AsyncResponse;
import com.example.http.models.Post;
import com.example.http.models.Posts;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvPosts;
    PostAdapter adapter;
    Posts posts;
    HttpAsync httpAsync;
    Button btnGet, btnGetParam, btnPost;
    EditText etId;

    private int option;
    private final int CODE_SAVE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){
        rvPosts = findViewById(R.id.rv_posts);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(llm);
        rvPosts.setHasFixedSize(true);

        posts = new Posts();

        adapter = new PostAdapter(MainActivity.this, posts.getPosts());

        rvPosts.setAdapter(adapter);

        etId = findViewById(R.id.edittext_id);

        btnGet = findViewById(R.id.button_get);
        btnGetParam = findViewById(R.id.button_getParam);
        btnPost = findViewById(R.id.button_post);

        btnGet.setOnClickListener(this);
        btnGetParam.setOnClickListener(this);
        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        option = v.getId();
        httpAsync = new HttpAsync(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                String data = output.toString();

                if (option == R.id.button_get){
                    posts.getPosts().addAll(posts.manyFromJson(data).getPosts());
                }else{
                    posts.getPosts().add(posts.oneFromJson(data));
                }

                adapter.notifyDataSetChanged();
            }
        });

        switch (v.getId()){
            case R.id.button_get: executeHttpGet();
            break;
            case R.id.button_getParam: executeHttpGetParam();
            break;
            case R.id.button_post: executeHttpPost();
            break;
        }
    }

    private void executeHttpGet(){
        httpAsync.execute(MainActivity.this, "get", "");
    }

    private void executeHttpGetParam(){
        String id = etId.getText().toString().trim();

        if (!id.isEmpty()){
            httpAsync.execute(MainActivity.this, "getparam", id);
        }
    }

    private void executeHttpPost(){
        Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
        startActivityForResult(intent, CODE_SAVE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String[] post = new String[4];

        if (requestCode == CODE_SAVE){
            post[0] = data.getStringExtra("id");
            post[1] = data.getStringExtra("userid");
            post[2] = data.getStringExtra("title");
            post[3] = data.getStringExtra("body");

            httpAsync.execute(MainActivity.this, "post", post);
        }
    }
}
