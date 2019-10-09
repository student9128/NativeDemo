package com.kevin.nativedemo;

import android.animation.ArgbEvaluator;
import android.os.Handler;

import androidx.viewpager.widget.ViewPager;

import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE;
import static com.kevin.nativedemo.SwipeDirectionDetector.Direction.LEFT;
import static com.kevin.nativedemo.SwipeDirectionDetector.Direction.RIGHT;


public abstract class ColorChangeEvaluatorListener implements ViewPager.OnPageChangeListener {

    private final SwipeDirectionDetector directionDetector;
    private final ArgbEvaluator evaluator;
    private final int[] colors;
    private int currentItem;
    private ViewPager viewPager;
    private float prevOffset;

    public ColorChangeEvaluatorListener(ViewPager viewPager, int[] colors) {
        this.viewPager = viewPager;
        currentItem = viewPager.getCurrentItem();
        evaluator = new ArgbEvaluator();
        directionDetector = new SwipeDirectionDetector();
        this.colors = colors;
        updateColor();
    }

    @Override
    public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
        directionDetector.onPageScrolled(position, offset, positionOffsetPixels);
        if (Math.abs(prevOffset - offset) > 0.3) {
            currentItem = viewPager.getCurrentItem();
        }
        prevOffset = offset;

        if (offset > 0) {
            updateColor(offset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        directionDetector.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        directionDetector.onPageScrollStateChanged(state);
        if (state == SCROLL_STATE_IDLE) {
            currentItem = viewPager.getCurrentItem();
            updateColor();
        }
    }

    private void updateColor(float offset) {
        int currentColor = colors[currentItem];

        int position = currentItem;
        if (directionDetector.getDirection() == RIGHT) {
            position = currentItem + 1;
        } else if (directionDetector.getDirection() == LEFT) {
            position = currentItem - 1;
            offset = 1 - offset;
        }
        position = Math.max(0, position);
        position = Math.min(colors.length - 1, position);
        int nextColor = colors[position];
        colorChanged((Integer) evaluator.evaluate(offset, currentColor, nextColor));
    }

    private void updateColor() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                colorChanged(colors[currentItem]);
            }
        });
//        new Handler().post(() -> colorChanged(colors[currentItem]));
    }

    public abstract void colorChanged(int color);
}