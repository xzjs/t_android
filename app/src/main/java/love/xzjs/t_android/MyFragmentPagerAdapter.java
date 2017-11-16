package love.xzjs.t_android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2017/11/15.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;
    private Show show;
    private MyFragment myFragment2, myFragment3, myFragment4;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        show = new Show();
        myFragment2 = new MyFragment();
        myFragment3 = new MyFragment();
        myFragment4 = new MyFragment();
        myFragment2.content = "2";
        myFragment3.content = "3";
        myFragment4.content = "4";
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = show;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
