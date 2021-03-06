package com.example.sunshine.weather1;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
//import androidx.core.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Leila on 5/15/2019.
 */

public class CustomPager extends ViewPager {
    public CustomPager(@NonNull Context context) {
        super(context);
    }

    public CustomPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            int height=0;
            for(int i=0; i<getChildCount(); i++){
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if(h>height) height=h;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
           // int currentPagePosition=0;
            //View child = getChildAt(getCurrentItem());
            //if (child != null) {
              //  child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                //int h = child.getMeasuredHeight();
                //heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
