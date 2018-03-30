package com.waifusystem.duplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideAdapter extends FragmentPagerAdapter {

    private int currentPosition;

    private ProfileFragment profileFragment;
    private ItemFragment itemFragment;



    public SlideAdapter(FragmentManager fragmentManager, ProfileFragment profileFragment, ItemFragment itemFragment) {
        super(fragmentManager);
        this.profileFragment = profileFragment;
        this.itemFragment = itemFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return profileFragment;
        } else {
            return itemFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
