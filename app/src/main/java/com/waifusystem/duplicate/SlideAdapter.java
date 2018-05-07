package com.waifusystem.duplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SlideAdapter extends PagerAdapter {

    private Context context;

    public static int[] imagesPath = new int[]{
            R.drawable.hdsd_22,
            R.drawable.hdsd_16,
            R.drawable.hdsd_17,
            R.drawable.hdsd_18,
            R.drawable.hdsd_19,
            R.drawable.hdsd_20,
            R.drawable.hdsdcuoi_21
   };

    @Override
    public int getCount() {
        return imagesPath.length;
    }

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView = view.findViewById(R.id.guide_image);
        slideImageView.setImageResource(imagesPath[position]);
        slideImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
