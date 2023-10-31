package jp.kt.balancebook.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import java.util.List;

import jp.kt.balancebook.model.Balance;
import jp.kt.balancebook.model.BalanceDao;
import jp.kt.balancebook.model.BalanceRoomDatabase;

public class BalanceRepository {

    private BalanceDao mBalanceDao;
    private LiveData<List<Balance>> mAllBalance;

    /**
     * WordRepository Constructor <br>
     *
     * @param application
     */
    public BalanceRepository(Application application) {
        BalanceRoomDatabase db = BalanceRoomDatabase.getDatabase(application);
        mBalanceDao = db.balanceDao();
        mAllBalance = mBalanceDao.getUpdateOrderBalance();
    }

    /**
     * get All Balance Query <br>
     * Room はすべてのクエリを別スレッドで実行する
     *
     * @return LiveData\<List\<Balance\>>
     */
    public LiveData<List<Balance>> getAllBalance() {
        return mAllBalance;
    }

    /**
     * Insert Query <br>
     * UIではないスレッドで呼び出す必要がある。<br>
     *
     * @param balance
     */
    public void insert(Balance balance) {
        BalanceRoomDatabase.sDatabaseWriteExecutor.execute(() ->
                mBalanceDao.insert(balance)
        );
    }
}
