package com.waifusystem.duplicate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageAndDescriptionFragment extends Fragment {

    private int id;

    TextView textView;
    ImageView imageView;


    public ImageAndDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_and_description, container, false);
        imageView = view.findViewById(R.id)
        return view;
    }

    public void setId(int id) {
        this.id = id;
    }

}
