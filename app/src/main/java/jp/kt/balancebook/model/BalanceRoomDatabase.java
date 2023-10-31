package jp.kt.balancebook.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Balance.class}, version = 1, exportSchema = false)
public abstract class BalanceRoomDatabase extends RoomDatabase {

    public abstract BalanceDao balanceDao();

    // volatile をつけるとキャッシュに保存されずにメインメモリから呼び出される
    private static volatile BalanceRoomDatabase sINSTANCE;
    private static final int sNUMBER_OF_THREADS = 4;
    public static final ExecutorService sDatabaseWriteExecutor =
            Executors.newFixedThreadPool(sNUMBER_OF_THREADS);

    public static BalanceRoomDatabase getDatabase(final Context context) {
        if (sINSTANCE == null) {
            synchronized (BalanceRoomDatabase.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    BalanceRoomDatabase.class,
                                    "balance_database")
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return sINSTANCE;
    }

    private static final Callback sRoomDatabaseCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            sDatabaseWriteExecutor.execute(() -> {
                BalanceDao dao = sINSTANCE.balanceDao();

                Balance balance = new Balance(100);
                dao.insert(balance);
                balance = new Balance(330);
                dao.insert(balance);
            });
        }
    };
}
