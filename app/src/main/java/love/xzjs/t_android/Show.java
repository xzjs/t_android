package love.xzjs.t_android;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class Show extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private TextView textViewTime, textViewDate, textViewWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show, container, false);

        textViewTime = (TextView) view.findViewById(R.id.time_label);
        textViewDate = (TextView)view.findViewById(R.id.date);
        textViewWeek = (TextView)view.findViewById(R.id.week);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        textViewTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150 * width / 1794);

        Switch switchTime = (Switch) view.findViewById(R.id.switch_time);
        Switch switchDate = (Switch) view.findViewById(R.id.switch_date);
        Switch switchWeek = (Switch) view.findViewById(R.id.switch_week);
        switchTime.setOnCheckedChangeListener(this);
        switchDate.setOnCheckedChangeListener(this);
        switchWeek.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_time:
                if (compoundButton.isChecked()) {
                    textViewTime.setVisibility(View.VISIBLE);
                } else {
                    textViewTime.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.switch_date:
                if (compoundButton.isChecked()) {
                    textViewDate.setVisibility(View.VISIBLE);
                } else {
                    textViewDate.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.switch_week:
                if (compoundButton.isChecked()) {
                    textViewWeek.setVisibility(View.VISIBLE);
                } else {
                    textViewWeek.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
