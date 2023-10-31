package jp.kt.balancebook.ui.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jp.kt.balancebook.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder {

    private View[] mCellList;

    public CalendarViewHolder(View itemView) {
        super(itemView);

        // 1月分のカレンダーの参照リストをセットアップ
        setupCalendarCell(itemView);
    }

    public void bind(ArrayList<LocalDate> dates, LocalDate currentMonth) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        for (int i = 0; i < 42; i++) {
            TextView textView = mCellList[i].findViewById(R.id.cell_date_text);
            textView.setText(formatter.format(dates.get(i)));
            if (dates.get(i).getMonthValue() != currentMonth.getMonthValue()) {
                textView.setAlpha(0.8F);
            }
        }
    }

    // 1月分のカレンダーの参照リストをセットアップ
    private void setupCalendarCell(View view) {
        View[] weeks = {
                view.findViewById(R.id.month_week_1), view.findViewById(R.id.month_week_2), view.findViewById(R.id.month_week_3),
                view.findViewById(R.id.month_week_4), view.findViewById(R.id.month_week_5), view.findViewById(R.id.month_week_6)
        };
        mCellList = new View[42];
        for (int i = 0; i < 6; i++) {
            View week = weeks[i];
            mCellList[i * 7] = setSundayTextColor(week.findViewById(R.id.week_day_1).findViewById(R.id.cell_date_text));
            mCellList[i * 7 + 1] = week.findViewById(R.id.week_day_2);
            mCellList[i * 7 + 2] = week.findViewById(R.id.week_day_3);
            mCellList[i * 7 + 3] = week.findViewById(R.id.week_day_4);
            mCellList[i * 7 + 4] = week.findViewById(R.id.week_day_5);
            mCellList[i * 7 + 5] = week.findViewById(R.id.week_day_6);
            mCellList[i * 7 + 6] = setSaturdayTextColor(week.findViewById(R.id.week_day_7).findViewById(R.id.cell_date_text));
        }
    }

    // R.color.light_highlight_label1 alpha 80%
    private TextView setSundayTextColor(TextView view) {
        view.setTextColor(Color.rgb(239, 83, 80));
        return view;
    }

    // R.color.light_highlight_label2 alpha 80%
    private TextView setSaturdayTextColor(TextView view) {
        view.setTextColor(Color.rgb(33, 150, 243));
        return view;
    }

}