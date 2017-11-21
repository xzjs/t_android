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
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;



public class Show extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private TextView textViewTime, textViewDate, textViewWeek;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show, container, false);

        textViewTime = (TextView) view.findViewById(R.id.time_label);
        textViewDate = (TextView)view.findViewById(R.id.date);
        textViewWeek = (TextView)view.findViewById(R.id.week);
        linearLayout1=(LinearLayout)view.findViewById(R.id.weather_today);
        linearLayout2=(LinearLayout)view.findViewById(R.id.weather_tomorrow);
        linearLayout3=(LinearLayout)view.findViewById(R.id.weather_after_tomorrow);

        //根据屏幕宽度确定字体的大小
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        textViewTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150 * width / 1794);

        //绑定switch事件
        Switch switchTime = (Switch) view.findViewById(R.id.switch_time);
        Switch switchDate = (Switch) view.findViewById(R.id.switch_date);
        Switch switchWeek = (Switch) view.findViewById(R.id.switch_week);
        Switch switchWeather1=(Switch)view.findViewById(R.id.switch_weather1);
        Switch switchWeather2=(Switch)view.findViewById(R.id.switch_weather2);
        Switch switchWeather3=(Switch)view.findViewById(R.id.switch_weather3);
        switchTime.setOnCheckedChangeListener(this);
        switchDate.setOnCheckedChangeListener(this);
        switchWeek.setOnCheckedChangeListener(this);
        switchWeather1.setOnCheckedChangeListener(this);
        switchWeather2.setOnCheckedChangeListener(this);
        switchWeather3.setOnCheckedChangeListener(this);

        return view;
    }

    /**
     * 重写switch状态变更事件
     * @param compoundButton
     * @param b
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_time:
                textViewTime.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
            case R.id.switch_date:
                textViewDate.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
            case R.id.switch_week:
                textViewWeek.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
            case R.id.switch_weather1:
                linearLayout1.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
            case R.id.switch_weather2:
                linearLayout2.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
            case R.id.switch_weather3:
                linearLayout3.setVisibility(compoundButton.isChecked()?View.VISIBLE:View.GONE);
                break;
        }
    }
}
