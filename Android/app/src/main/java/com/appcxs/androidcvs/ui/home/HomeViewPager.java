package com.appcxs.androidcvs.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.appcxs.androidcvs.ui.home.all_news.NewsFragment;
import com.appcxs.androidcvs.ui.home.list_categrory.CategoryFragment;

public class HomeViewPager extends FragmentStateAdapter {


    public HomeViewPager(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new NewsFragment();
        else
            return new CategoryFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
