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

import jp.kt.balancebook.R;
import jp.kt.balancebook.databinding.FragmentSettingBinding;
import jp.kt.balancebook.databinding.ItemBottomNavigationViewBinding;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding mBinding;
    private int mBackground1, mBackground2;
    private int mLayoutWidth;
    private int mLayoutHeight;

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
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);

        // Bottom Navigation View の選択されているアイテムの背景色を変更する
        setBottomNavViewLayout();

        // タイトルの文字列を設定
        mBinding.includeBackButtonAppBar.titleText.setText("設定");

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // App barのバックボタンが押された時の処理
        mBinding.includeBackButtonAppBar.backButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );

        // BottomNavigationViewのアクション設定
        mBinding.includeBottomNavView.homeButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_setting_to_calendar)
        );
        mBinding.includeBottomNavView.statsButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_setting_to_stats)
        );
    }

    // Bottom Navigation View の選択されているアイテムの背景色を変更する
    private void setBottomNavViewLayout() {
        ItemBottomNavigationViewBinding navView = mBinding.includeBottomNavView;
        navView.homeCard.setCardBackgroundColor(mBackground1);
        navView.statsCard.setCardBackgroundColor(mBackground1);
        navView.settingCard.setCardBackgroundColor(mBackground2);
    }
}