package com.example.http.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.http.R;
import com.example.http.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public class PostViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView tvId,tvUserId,tvTitle,tvBody;

        PostViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);

            tvId = itemView.findViewById(R.id.textview_id);
            tvUserId = itemView.findViewById(R.id.textview_userid);
            tvTitle = itemView.findViewById(R.id.textview_title);
            tvBody = itemView.findViewById(R.id.textview_body);
        }
    }

    List<Post> posts;
    Context context;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final PostViewHolder pvh = new PostViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.tvId.setText(String.valueOf(posts.get(i).getId()));
        postViewHolder.tvUserId.setText(String.valueOf(posts.get(i).getUserId()));
        postViewHolder.tvTitle.setText(posts.get(i).getTitle());
        postViewHolder.tvBody.setText(posts.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
