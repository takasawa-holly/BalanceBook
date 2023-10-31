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

import jp.kt.balancebook.R;
import jp.kt.balancebook.databinding.FragmentCalendarBinding;
import jp.kt.balancebook.databinding.ItemBottomNavigationViewBinding;
import jp.kt.balancebook.manager.CalendarDateManager;
import jp.kt.balancebook.ui.adapter.CalendarPagerAdapter;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding mBinding;
    private int mBackground1, mBackground2;

    private CalendarDateManager mDateManager;

    public CalendarFragment() {
    }

    @Override
    public void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        mBackground1 = ResourcesCompat.getColor(getResources(), R.color.light_background1, null);
        mBackground2 = ResourcesCompat.getColor(getResources(), R.color.light_background2, null);

        // Calendar Date Manager
        mDateManager = CalendarDateManager.getInstance();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        mBinding = FragmentCalendarBinding.inflate(inflater, container, false);

        // Bottom Navigation View の選択されているアイテムの背景色を変更する
        setBottomNavViewLayout();

        // 今年の月数を動的に取得すし、過去の月数(24)と足し合わせる
        int CalendarPosition = mDateManager.getToday().getMonthValue();
        ViewPager2 pager = mBinding.calendarViewPager;
        pager.setCurrentItem(24 + CalendarPosition);
        CalendarPagerAdapter adapter = new CalendarPagerAdapter();
        pager.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // イベントをまとめて宣言
        setupEventListener();
    }

    // Bottom Navigation View の選択されているアイテムの背景色を変更する
    private void setBottomNavViewLayout() {
        ItemBottomNavigationViewBinding navView = mBinding.includeBottomNavView;
        navView.homeCard.setCardBackgroundColor(mBackground2);
        navView.statsCard.setCardBackgroundColor(mBackground1);
        navView.settingCard.setCardBackgroundColor(mBackground1);
    }

    // イベントをまとめて宣言
    private void setupEventListener() {
        // FAB Buttonが押された時のイベントリスナー - Record Fragmentに遷移
        mBinding.calendarFab.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_calendar_to_record)
        );

        // BottomNavigationViewのアクション設定
        mBinding.includeBottomNavView.statsButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_calendar_to_stats)
        );
        mBinding.includeBottomNavView.settingCard.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_calendar_to_setting)
        );
    }
}