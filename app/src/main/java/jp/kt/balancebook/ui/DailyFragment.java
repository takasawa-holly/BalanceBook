package jp.kt.balancebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import jp.kt.balancebook.R;
import jp.kt.balancebook.databinding.FragmentDailyBinding;

public class DailyFragment extends Fragment {

    private FragmentDailyBinding mBinding;

    public DailyFragment() {
    }

    @Override
    public void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        mBinding = FragmentDailyBinding.inflate(inflater, container, false);

        // Top App Barのタイトルを変更
        mBinding.includeTopAppBarDaily.titleText.setText("2023年 2月8日 (水)");

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // Back Button - Pop Back
        mBinding.includeTopAppBarDaily.backButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );

        // FAB Button - navigate to Record Fragment
        mBinding.dailyFab.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_daily_to_record)
        );

        // BottomNavigationViewのアクション設定
        mBinding.includeBottomNavView.homeButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );
        mBinding.includeBottomNavView.statsButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_daily_to_stats)
        );
        mBinding.includeBottomNavView.settingCard.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_daily_to_setting)
        );
    }
}