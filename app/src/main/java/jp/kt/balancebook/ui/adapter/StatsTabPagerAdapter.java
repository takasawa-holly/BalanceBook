package jp.kt.balancebook.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import jp.kt.balancebook.ui.tabitem.StatsTabItemExpendFragment;
import jp.kt.balancebook.ui.tabitem.StatsTabItemIncomeFragment;

public class StatsTabPagerAdapter extends FragmentStateAdapter {

    public StatsTabPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = (position == 0) ? new StatsTabItemExpendFragment() : new StatsTabItemIncomeFragment();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
