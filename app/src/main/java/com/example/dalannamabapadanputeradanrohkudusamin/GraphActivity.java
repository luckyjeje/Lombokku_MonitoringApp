package com.example.dalannamabapadanputeradanrohkudusamin;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private LineChart mChart;
    private MaterialButton btnStartMonitoring, btnStopMonitoring;
    private static List<HistoryModel> historyModelList = new ArrayList<>();
    private DatabaseReference reference;
    private IMarker marker;
    private boolean monitoringStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        marker = new CustomMarkerView(GraphActivity.this, R.layout.custom_marker_view);
        btnStartMonitoring = findViewById(R.id.btn_start);
        btnStopMonitoring = findViewById(R.id.btn_stop);

        reference = FirebaseDatabase.getInstance().getReference();

        reference.child("history/humid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() > historyModelList.size()) {
                    int index = 0;
                    for(DataSnapshot ds: snapshot.getChildren()) {
                        HistoryModel history = ds.getValue(HistoryModel.class);
                        if(history != null && index > historyModelList.size() - 1) {
                            historyModelList.add(history);
                            addEntry(history.value);
                        }
                        index++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("humid/humid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String humid = String.valueOf(snapshot.getValue(Float.class));
                HistoryModel historyModel = new HistoryModel(Util.getCurrentTime(), humid);
                if(monitoringStatus) {
                    reference.child("history/humid").push().setValue(historyModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnStartMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monitoringStatus = true;
                btnStartMonitoring.setVisibility(View.INVISIBLE);
                btnStopMonitoring.setVisibility(View.VISIBLE);
            }
        });

        btnStopMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monitoringStatus = false;
                btnStartMonitoring.setVisibility(View.VISIBLE);
                btnStopMonitoring.setVisibility(View.INVISIBLE);
            }
        });

        mChart = findViewById(R.id.chart1);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        setupChart();
        setupAxes();
        setupData();
        setLegend();
    }

    private void setupChart() {
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        // enable scaling
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        // set an alternative background color
        mChart.setBackgroundColor(Color.DKGRAY);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setMarker(marker);
    }

    private void setupAxes() {
        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setValueFormatter(new CustomLabelDateTimeFormatter());
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setupData() {
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        mChart.setData(data);
    }

    private void setLegend() {
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTextColor(Color.WHITE);
    }

    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Humid Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColors(ColorTemplate.VORDIPLOM_COLORS[0]);
        set.setCircleColor(Color.GREEN);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);
        set.setHighLightColor(Color.WHITE);
        set.setDrawHighlightIndicators(true);
        // To show values of each point
        set.setDrawValues(true);

        return set;
    }

    private void addEntry(String value) {
        LineData data = mChart.getData();

        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }


            data.addEntry(new Entry(set.getEntryCount(), Float.parseFloat(value)), 0);
            data.setHighlightEnabled(true);

            // let the chart know it's data has changed
            data.notifyDataChanged();
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
//            mChart.setVisibleXRangeMaximum(15);

            // move to the latest entry
            mChart.moveViewToX(data.getEntryCount());
            mChart.getXAxis().setLabelCount(data.getEntryCount(), true);
            if(data.getEntryCount() > 1) {
                mChart.getXAxis().setCenterAxisLabels(false);
            } else {
                mChart.getXAxis().setCenterAxisLabels(true);
            }
        }
    }

    static class CustomLabelDateTimeFormatter extends ValueFormatter {

        @Override
        public String getFormattedValue(float value) {
           return "";
        }
    }

    static class CustomMarkerView extends MarkerView {

        /**
         * Constructor. Sets up the MarkerView with a custom layout resource.
         *
         * @param context
         * @param layoutResource the layout resource to use for the MarkerView
         */
//        private List<HistoryModel> historyModelList = new ArrayList<>();
        private TextView tvDateTime, tvValue;

        public CustomMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            tvDateTime = findViewById(R.id.tv_datetime);
            tvValue = findViewById(R.id.tv_value);
        }

        public void addHistory(HistoryModel historyModel) {
            historyModelList.add(historyModel);
        }

        // callbacks everytime the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            HistoryModel history = historyModelList.get( (int) highlight.getX());
            tvDateTime.setText("Datetime: " + history.time); // set the entry-value as the display text
            tvValue.setText("Value: " + history.value);
            super.refreshContent(e, highlight);
        }

        private MPPointF mOffset;

        @Override
        public MPPointF getOffset() {

            if(mOffset == null) {
                // center the marker horizontally and vertically
                mOffset = new MPPointF(-(getWidth() / 2), -(getHeight() + 10));
            }

            return mOffset;
        }

        @Override
        public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
            if(posX > getWidth()) {
                return new MPPointF(-(getWidth()), -(getHeight() + 10));
            }
            return super.getOffsetForDrawingAtPoint(posX, posY);
        }
    }
}
