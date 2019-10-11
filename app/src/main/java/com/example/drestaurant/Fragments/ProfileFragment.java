package com.example.drestaurant.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.drestaurant.Interfaces.ImageConverter;
import com.example.drestaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View root;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_person);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView) root.findViewById(R.id.image);
        circularImageView.setImageBitmap(circularBitmap);
        return root;
    }

}
