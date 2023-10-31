package jp.kt.balancebook.ui.tabitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import jp.kt.balancebook.databinding.FragmentRecordTabItemExpendBinding;

public class RecordTabItemExpendFragment extends Fragment {

    private FragmentRecordTabItemExpendBinding mBinding;

    public RecordTabItemExpendFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentRecordTabItemExpendBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // キャンセルボタン - ポップバック
        mBinding.recordExpendCancelButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );

        // TODO: 保存ボタン - セーブデータベース
        mBinding.recordExpendSaveButton.setOnClickListener(v ->
                Navigation.findNavController(v).popBackStack()
        );
    }
}