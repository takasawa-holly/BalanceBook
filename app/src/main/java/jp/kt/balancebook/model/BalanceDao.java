package jp.kt.balancebook.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BalanceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Balance balance);

    @Query("DELETE FROM balance_table")
    void deleteAll();

    /**
     * 更新日の降順ですべてのデータを取得する
     *
     * @return LiveData\<List\<Balance\>\>
     */
    @Query("SELECT * FROM balance_table ORDER BY update_date DESC")
    LiveData<List<Balance>> getUpdateOrderBalance();
}
