package jp.kt.balancebook.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import jp.kt.balancebook.repository.BalanceRepository;

public class CalendarViewModel extends AndroidViewModel {

    private BalanceRepository mRepository;
    private final LiveData<List<Balance>> mAllBalance;

    public CalendarViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BalanceRepository(application);
        mAllBalance = mRepository.getAllBalance();
    }

    public LiveData<List<Balance>> getAllBalance() {
        return mAllBalance;
    }

    public void insert(Balance balance) {
        mRepository.insert(balance);
    }
}