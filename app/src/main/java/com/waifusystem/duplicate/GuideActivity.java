package com.waifusystem.duplicate;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = findViewById(R.id.slide_view_pager);
        slideAdapter = new SlideAdapter(this);
        final Button button = findViewById(R.id.finish_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, QuestActivity.class);
                startActivity(intent);
            }
        });

        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == SlideAdapter.imagesPath.length - 1) {
                    button.setVisibility(View.VISIBLE);
                    button.setActivated(true);
                } else {
                    button.setVisibility(View.GONE);
                    button.setActivated(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
