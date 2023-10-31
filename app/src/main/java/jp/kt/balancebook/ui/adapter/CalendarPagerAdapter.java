package jp.kt.balancebook.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import jp.kt.balancebook.R;
import jp.kt.balancebook.manager.CalendarDateManager;
import jp.kt.balancebook.ui.viewholder.CalendarViewHolder;

public class CalendarPagerAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private CalendarDateManager mDateManager;
    private ArrayList<LocalDate> mCalendarMonths;

    public CalendarPagerAdapter() {
        mDateManager = CalendarDateManager.getInstance();
        mCalendarMonths = mDateManager.getCalendarMonths();
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_calendar_month, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        LocalDate month = mCalendarMonths.get(position);
        mDateManager.setDisplayDate(month);
        ArrayList<LocalDate> dates = mDateManager.getDisplayDays(month);
        holder.bind(dates, month);
    }

    @Override
    public int getItemCount() {
        return 42;
    }
}
