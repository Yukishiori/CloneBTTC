package com.waifusystem.duplicate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageAndDescriptionFragment extends Fragment {

    private int id;

    TextView description;
    ImageView profileImage;


    public ImageAndDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_and_description, container, false);
        profileImage = view.findViewById(R.id.profile_image);
        description = view.findViewById(R.id.item_description);

        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in_anim);


        Profile profile = Profile.profiles[id];
        profileImage.setImageResource(profile.getItemImagePath());
        description.setText(profile.getDescription());

        profileImage.setAnimation(animation);
        description.setAnimation(animation);

        return view;
    }

    public void setId(int id) {
        this.id = id;
    }

}
