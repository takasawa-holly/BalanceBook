package jp.kt.balancebook;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import jp.kt.balancebook.databinding.ActivityMainBinding;
import jp.kt.balancebook.manager.CalendarDateManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding mBinding;
    private NavController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main Binding
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Navigation Controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        mController = Objects.requireNonNull(navHostFragment).getNavController();

        mAppBarConfiguration = new AppBarConfiguration.Builder(mController.getGraph()).build();

        // CalendarDateManagerのインスタンスをセットアップ
        CalendarDateManager.setupInstance();
    }

    // ツールバーの戻るボタンを押したときのナビゲーションを処理してくれる
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}