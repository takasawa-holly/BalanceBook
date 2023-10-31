package jp.kt.balancebook.ui.tabitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import jp.kt.balancebook.R;
import jp.kt.balancebook.databinding.FragmentStatsTabItemIncomeBinding;

public class StatsTabItemIncomeFragment extends Fragment {

    private FragmentStatsTabItemIncomeBinding mBinding;
    private PieChart mPieChart;
    private int mChartLabelColor, mChartColor01, mChartColor02, mChartColor03;

    public StatsTabItemIncomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChartLabelColor = ResourcesCompat.getColor(getResources(), R.color.light_label2, null);
        mChartColor01 = ResourcesCompat.getColor(getResources(), R.color.chart1, null);
        mChartColor02 = ResourcesCompat.getColor(getResources(), R.color.chart2, null);
        mChartColor03 = ResourcesCompat.getColor(getResources(), R.color.chart3, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStatsTabItemIncomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        mPieChart = mBinding.pieChart;
        setupPieChartView();

    }

    private void setupPieChartView() {

        //グラフのデータを設定
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(1F, "item 1"));
        pieEntries.add(new PieEntry(1F, "item 2"));

        //表示する色を設定
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(mChartColor01);
        colors.add(mChartColor02);

        //chartに設定
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setDrawValues(false);
        dataSet.setValueTextSize(11F);
        mPieChart.setData(new PieData(dataSet));

        // 説明テキストの非表示
        mPieChart.getDescription().setEnabled(false);

        Legend legend = mPieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextSize(10F);
        legend.setTextColor(mChartLabelColor);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setDrawInside(false);
        legend.setTextColor(mChartLabelColor);

        // PieChartの穴のサイズ設定
        mPieChart.setHoleRadius(20F);
        mPieChart.setTransparentCircleRadius(25F);

        // チャートの表示
        mPieChart.setEntryLabelColor(mChartLabelColor);
        mPieChart.invalidate();
    }
}