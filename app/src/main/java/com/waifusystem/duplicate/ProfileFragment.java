package com.waifusystem.duplicate;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {
    private int profileId;

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

        configProfileStuff();
    }

    private void configProfileStuff() {
        Profile profile = Profile.profiles[profileId];
        View view = getView();
        TextView personName = view.findViewById(R.id.profile_name);
        personName.setText(profile.getName());
        TextView description = view.findViewById(R.id.description);
        description.setText(profile.getDescription());
        TextView tag = view.findViewById(R.id.tag);
        tag.setText(profile.getTag());
    }

    public void setProfile(int id) {
        this.profileId = id;
    }


}
