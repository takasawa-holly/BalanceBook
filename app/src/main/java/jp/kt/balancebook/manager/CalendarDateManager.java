package jp.kt.balancebook.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarDateManager {

    private static CalendarDateManager mInstance;
    private static LocalDate mDisplayDate;
    private LocalDate mTodayDate;
    private ArrayList<LocalDate> mCalendarMonths;

    // 1画面に表示するCalendarItemの表示数
    final private int CALENDAR_DAYS = 42;

    // コンストラクタをプライベートにして、新しくインスタンスを作成するのを防ぐ
    private CalendarDateManager() {
        mTodayDate = mDisplayDate = LocalDate.now();
        mCalendarMonths = new ArrayList<>();
    }

    // ただインスタンスを作成する
    public static void setupInstance() {
        if (mInstance == null) {
            mInstance = new CalendarDateManager();
        }
    }

    // このメソッドからしかインスタンスを作成できないようにする
    public static CalendarDateManager getInstance() {
        if (mInstance == null) {
            mInstance = new CalendarDateManager();
        }
        return mInstance;
    }

    /**
     * カレンダーに表示する月のリストを取得します。
     * 現在の日付から2年前の1月1日を開始日とし、5年分（60か月分）の月をリストに格納します。
     * リストが空の場合にのみ、新たにリストを作成して返します。
     *
     * @return カレンダーに表示する月のリスト
     */
    public ArrayList<LocalDate> getCalendarMonths() {
        if (mCalendarMonths.isEmpty()) {
            // 2年前の1月1日を取得
            LocalDate _date = mTodayDate.minusYears(2).withMonth(1).withDayOfMonth(1);
            // 60か月分(5年分)のカレンダーに表示する日付データのリストを作成
            for (int i = 0; i < 60; i++) {
                mCalendarMonths.add(_date);
                _date = nextMonth(_date);
            }
        }
        return mCalendarMonths;
    }

    /**
     * 指定された日付を含む月に表示される全ての日付のリストを取得します。
     * カレンダーに表示される前月分および翌月分の日付も含めてリストに追加します。
     * 例えば、月の1日目が水曜日の場合、月曜日と火曜日に前月の日付が表示されます。
     *
     * @param date 表示対象の月の任意の日付
     * @return 指定された月に表示される日付のリスト
     */
    public ArrayList<LocalDate> getDisplayDays(LocalDate date) {

        LocalDate _startDate = date;

        // カレンダーに表示される前月分の日付を計算
        _startDate = LocalDate.of(_startDate.getYear(), _startDate.getMonth(), 1);
        int _dayOfWeek = _startDate.getDayOfWeek().getValue();
        _startDate = _startDate.minusDays(_dayOfWeek);

        ArrayList<LocalDate> _days = new ArrayList<>();
        for (int i = 0; i < CALENDAR_DAYS; i++) {
            _days.add(_startDate);
            _startDate = _startDate.plusDays(1);
        }
        return _days;
    }

    // 表示されている日付とManagerの日付があっているかどうか確認
    public boolean isCurrentMonth(LocalDate _localDate) {
        return _localDate.getMonth() == mDisplayDate.getMonth();
    }

    // 本日かどうかを確認する
    public boolean isCurrentToday(LocalDate _localDate) {
        return _localDate.isEqual(LocalDate.now());
    }

    /**
     * 表示対象の日付を設定します。
     * このメソッドは、カレンダーに表示する月を変更する際に使用します。
     *
     * @param _date 表示対象の日付
     */
    public void setDisplayDate(LocalDate _date) {
        mDisplayDate = _date;
    }

    // 現在の年月を文字列で返す
    public String getCurrentMonthStr() {
        return String.format(Locale.JAPAN, "%4d年 %2d月", mDisplayDate.getYear(), mDisplayDate.getMonthValue());
    }

    // 数値で現在の曜日を返す ( 日 ~ 土 : 0 ~ 6 )
    public int getDayOfWeek(LocalDate _date) {
        return _date.getDayOfWeek().getValue();
    }

    // 翌月の取得
    public LocalDate nextMonth(LocalDate _date) {
        return _date.plusMonths(1);
    }

    // 前月の取得
    public LocalDate prevMonth(LocalDate _date) {
        return _date.minusMonths(1);
    }

    // 今日の取得
    public LocalDate getToday() {
        return mTodayDate;
    }

}
