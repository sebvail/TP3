package com.example.snowt.omnipucks;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.reflect.Field;

/**
 * Created by snowt on 2016-11-28.
 */

public class NonSwipeableViewPager extends ViewPager {

    public NonSwipeableViewPager (Context context){
        super(context);

    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs){
        super(context,attrs);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return false;
    }


}
