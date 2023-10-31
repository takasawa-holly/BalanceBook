package jp.kt.balancebook.ui.tabitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import jp.kt.balancebook.databinding.FragmentRecordTabItemIncomeBinding;

public class RecordTabItemIncomeFragment extends Fragment {

    private FragmentRecordTabItemIncomeBinding mBinding;

    public RecordTabItemIncomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentRecordTabItemIncomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // キャンセルボタン - ポップバック
        mBinding.recordIncomeCancelButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );

        // TODO: 保存ボタン - セーブデータベース
        mBinding.recordIncomeSaveButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );
    }
}