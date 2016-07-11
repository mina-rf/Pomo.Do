package com.sharif.PomoDo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by mina on 7/9/16.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new PomodoroFragment();
            case 1:
                return new ToDoListFragment();
            case 2:
                return new StatisticsFragment();
            case 3:
                return new ToDoListTestFragment();
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab" + (position + 1);
    }
}



