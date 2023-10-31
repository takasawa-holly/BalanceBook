package jp.kt.balancebook.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import jp.kt.balancebook.ui.tabitem.RecordTabItemExpendFragment;
import jp.kt.balancebook.ui.tabitem.RecordTabItemIncomeFragment;

public class RecordTabPagerAdapter extends FragmentStateAdapter {

    public RecordTabPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = (position == 0) ? new RecordTabItemExpendFragment() : new RecordTabItemIncomeFragment();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
