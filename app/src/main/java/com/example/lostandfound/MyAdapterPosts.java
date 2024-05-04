package com.example.lostandfound;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapterPosts extends RecyclerView.Adapter<MyAdapterPosts.ViewHolder> {

    ArrayList<modelPosts> Posts;
    private ViewHolder selectedViewHolder;
    MyAdapterPosts()
    {
        Posts=new ArrayList<>();
    }
    MyAdapterPosts(ArrayList<modelPosts> P)
    {
        Posts=P;
    }

    public void setSelectedViewHolder(ViewHolder viewHolder) {
        this.selectedViewHolder = viewHolder;
    }

    public ViewHolder getSelectedViewHolder() {
        return selectedViewHolder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_post,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvItemName.setText(Posts.get(position).getItemName());
        holder.tvLocation.setText(Posts.get(position).getLocation());
        holder.tvStatus.setText(Posts.get(position).getStatus());
        holder.tvNameProfile.setText(Posts.get(position).getPostedBy());
        holder.tvPostTime.setText(Posts.get(position).getPostTime());

        // Load profile picture
        Glide.with(holder.itemView.getContext())
                .load(Posts.get(position).getPostedByPicUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.ivProfilePost);

        // Load item picture
        Glide.with(holder.itemView.getContext())
                .load(Posts.get(position).getItemPostImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return Posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImage, ivProfilePost;
        TextView tvItemName,tvNameProfile, tvStatus,tvLocation,tvPostTime;
        Button btnView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init();
        }
        protected void init()
        {

            tvItemName=itemView.findViewById(R.id.tvItemName);
            tvNameProfile=itemView.findViewById(R.id.tvNameProfile);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            btnView=itemView.findViewById(R.id.btnView);
            tvPostTime=itemView.findViewById(R.id.tvPostTime);
            ivImage=itemView.findViewById(R.id.ivImage);
            ivProfilePost=itemView.findViewById(R.id.ivProfilePost);

        }

        @Override
        public void onClick(View v) {

    }

    }
}
