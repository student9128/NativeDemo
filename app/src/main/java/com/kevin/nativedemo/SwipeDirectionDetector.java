package com.kevin.nativedemo;


import androidx.viewpager.widget.ViewPager;


public class SwipeDirectionDetector implements ViewPager.OnPageChangeListener {
    private Direction direction;
    private int lastState;

    public enum Direction {
        LEFT,
        RIGHT,
        NO_DIRECTION
    }

    @Override
    public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
        boolean leftStarted = direction == Direction.LEFT && offset > 0.5;
        boolean rightStarted = direction == Direction.RIGHT && offset < 0.5;
        if (lastState == ViewPager.SCROLL_STATE_DRAGGING && (leftStarted || rightStarted)) {
            direction = Direction.NO_DIRECTION;
        }
        initDirection(offset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        lastState = state;
    }

    private void initDirection(float offset) {
        if (direction == Direction.NO_DIRECTION && offset > 0) {
            if (offset > 0.5) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }
        }
        if (offset == 0) {
            direction = Direction.NO_DIRECTION;
        }
    }

    public Direction getDirection() {
        return direction;
    }
}