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
public class ItemFragment extends Fragment {

    private int profileId;
    Profile profile ;
    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onStart() {
        profile = Profile.profiles[profileId];
        super.onStart();
        View view = getView();
        if (view != null) {
            ImageView imageView = view.findViewById(R.id.imageView4);
            imageView.setImageResource(profile.getItemPicId());
            TextView textView = view.findViewById(R.id.description);
            textView.setText(profile.getItemDes());
        }
    }

    public void setProfileId(int id) {
        this.profileId = id;
    }

}
