package com.waifusystem.duplicate;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private int profileId;
    private ImageView profilePic;
    private TextView personName;
    private TextView description;
    private Profile profile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        profile = Profile.profiles[profileId];
        configProfileStuff();
    }

    private void configProfileStuff() {
        View view = getView();
        if (view != null) {
            profilePic = view.findViewById(R.id.person_pic);
            personName = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.profile_description);
            profilePic.setImageResource(profile.getProfilePicPath());
            personName.setText(profile.getName());
            description.setText(profile.getDescription());
        }
    }

    public void setProfile(int id) {
        this.profileId = id;
    }

}
