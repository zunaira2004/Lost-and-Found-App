package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    ArrayList<modelPosts> Posts;
    MyAdapterPosts myAdapter;
    RecyclerView rvPosts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        init(rootView);

        getDataFromDb();

        return rootView;
    }
    protected void init(View rootView)
    {
        rvPosts = rootView.findViewById(R.id.rvPosts);

        Posts=new ArrayList<>();

        rvPosts.setHasFixedSize(true);

        myAdapter = new MyAdapterPosts(Posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPosts.setAdapter(myAdapter);
    }
    protected void getDataFromDb()
    {
        DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Posts");

        postsRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear the existing list before adding new data
                Posts.clear();

                // Iterate through the dataSnapshot to retrieve each post
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    // Convert the dataSnapshot to a model object and add it to the list
                    modelPosts post = postSnapshot.getValue(modelPosts.class);

                    Posts.add(post);
                }

                // Notify the adapter of the data change
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

}