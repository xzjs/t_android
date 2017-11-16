package love.xzjs.t_android;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioGroup radioGroup;
    private RadioButton radioButtonShow, radioButtonClock, radioButtonTime, radioButtonMy;
    private ViewPager viewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int mCurrentOrientation = getResources().getConfiguration().orientation;

        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "port");
            setContentView(R.layout.config);
            myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            bindViews();
            radioButtonShow.setChecked(true);

        } else {
            Log.i("info", "land");
            setContentView(R.layout.activity_main);
        }
    }

    private void bindViews() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        radioButtonShow = (RadioButton) findViewById(R.id.show);
        radioButtonClock = (RadioButton) findViewById(R.id.clock);
        radioButtonTime = (RadioButton) findViewById(R.id.time);
        radioButtonMy = (RadioButton) findViewById(R.id.my);

        viewPager = (ViewPager) findViewById(R.id.vpager);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.show:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.clock:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.time:
                viewPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.my:
                viewPager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager#SCROLL_STATE_IDLE
     * @see ViewPager#SCROLL_STATE_DRAGGING
     * @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    radioButtonShow.setChecked(true);
                    break;
                case PAGE_TWO:
                    radioButtonClock.setChecked(true);
                    break;
                case PAGE_THREE:
                    radioButtonTime.setChecked(true);
                    break;
                case PAGE_FOUR:
                    radioButtonMy.setChecked(true);
                    break;
            }
        }
    }
}
