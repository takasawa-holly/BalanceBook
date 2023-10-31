package jp.kt.balancebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import jp.kt.balancebook.databinding.FragmentRecordBinding;
import jp.kt.balancebook.ui.adapter.RecordTabPagerAdapter;

public class RecordFragment extends Fragment {

    private FragmentRecordBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        mBinding = FragmentRecordBinding.inflate(inflater, container, false);

        // ViewPager2とTabLayoutの初期化
        ViewPager2 pager = mBinding.recodePager;
        RecordTabPagerAdapter adapter = new RecordTabPagerAdapter(this);
        pager.setAdapter(adapter);

        TabLayout tabs = mBinding.recodeTabLayout;
        // アイテムが選択された時、フラグメントのタイトルを変更する
        tabs.addOnTabSelectedListener(onTabSelected());
        // タブのタイトルを振り分ける
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
        mBinding.includeTopAppBarRecord.backButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );
    }

    // アイテムが選択された時、フラグメントのタイトルを変更する
    private TabLayout.OnTabSelectedListener onTabSelected() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String titleName = "";
                titleName = (tab.getPosition() == 0) ? "支出" : "収入";
                mBinding.includeTopAppBarRecord.titleText.setText(titleName);
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