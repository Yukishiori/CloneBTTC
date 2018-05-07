package com.waifusystem.duplicate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSlideAdapter extends FragmentPagerAdapter {

    private int currentPosition;

    private ProfileFragment profileFragment;
    private AudioControllerFragment audioControllerFragment;



    public FragmentSlideAdapter(FragmentManager fragmentManager, ProfileFragment profileFragment, AudioControllerFragment audioControllerFragment) {
        super(fragmentManager);
        this.profileFragment = profileFragment;
        this.audioControllerFragment = audioControllerFragment;
    }



    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return profileFragment;
        } else {
            return audioControllerFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
