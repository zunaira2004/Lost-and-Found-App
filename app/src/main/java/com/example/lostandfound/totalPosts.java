package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class totalPosts extends Fragment {

    RecyclerView rvTotalPostsProfile;
    ProfilePostsAdaptor myAdaptor;
    ArrayList<String> postsImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_total_posts, container, false);
        init(view);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String currentUserId = currentUser.getUid();

            DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
            Query query = postsRef.orderByChild("userId").equalTo(currentUserId);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String imageUrl = postSnapshot.child("itemPostImageUrl").getValue(String.class);
                        if (imageUrl != null) {
                            postsImg.add(imageUrl);
                            Log.d("image url",imageUrl);
                        }
                    }
                    myAdaptor.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }

        return view;

    }
    void init(View view)
    {
        rvTotalPostsProfile=view.findViewById(R.id.rvTotalPostsProfile);
        postsImg=new ArrayList<>();

        rvTotalPostsProfile.setLayoutManager(new GridLayoutManager(getContext(),3));

        myAdaptor=new ProfilePostsAdaptor(postsImg,getActivity());
        rvTotalPostsProfile.setAdapter(myAdaptor);
    }
}