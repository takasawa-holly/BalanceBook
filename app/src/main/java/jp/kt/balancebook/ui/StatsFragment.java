package jp.kt.balancebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import jp.kt.balancebook.R;
import jp.kt.balancebook.databinding.FragmentStatsBinding;
import jp.kt.balancebook.databinding.ItemBottomNavigationViewBinding;
import jp.kt.balancebook.ui.adapter.StatsTabPagerAdapter;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding mBinding;
    private int mBackground1, mBackground2;

    private int mLayoutWidth;
    private int mLayoutHeight;

    public StatsFragment() {
    }

    @Override
    public void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        mBackground1 = ResourcesCompat.getColor(getResources(), R.color.light_background1, null);
        mBackground2 = ResourcesCompat.getColor(getResources(), R.color.light_background2, null);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        mBinding = FragmentStatsBinding.inflate(inflater, container, false);

        // Bottom Navigation View の選択されているアイテムの背景色を変更する
        setBottomNavViewLayout();

        // ViewPager2とTabLayoutの初期化
        ViewPager2 pager = mBinding.statsPager;
        StatsTabPagerAdapter adapter = new StatsTabPagerAdapter(this);
        pager.setAdapter(adapter);

        TabLayout tabs = mBinding.statsTabLayout;
        // アイテムが選択された時、フラグメントのタイトルを変更する
        tabs.addOnTabSelectedListener(onTabSelected());
        new TabLayoutMediator(tabs, pager, (tab, position) -> {
            String tabName = "";
            tabName = (position == 0) ? "支出" : "収入";
            tab.setText(tabName);
        }).attach();

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // App Bar のバックボタン
        mBinding.includeTopAppBarStats.backButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );

        // BottomNavigationViewのアクション設定
        mBinding.includeBottomNavView.homeButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_stats_to_calendar)
        );
        mBinding.includeBottomNavView.settingCard.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_stats_to_setting)
        );
    }

    // Bottom Navigation View の選択されているアイテムの背景色を変更する
    private void setBottomNavViewLayout() {
        ItemBottomNavigationViewBinding navView = mBinding.includeBottomNavView;
        navView.homeCard.setCardBackgroundColor(mBackground1);
        navView.statsCard.setCardBackgroundColor(mBackground2);
        navView.settingCard.setCardBackgroundColor(mBackground1);
    }

    // アイテムが選択された時、フラグメントのタイトルを変更する
    private TabLayout.OnTabSelectedListener onTabSelected() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String titleName = "";
                titleName = (tab.getPosition() == 0) ? "支出" : "収入";
                mBinding.includeTopAppBarStats.titleText.setText(titleName);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        };
    }
}