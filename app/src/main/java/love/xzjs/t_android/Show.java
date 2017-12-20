package love.xzjs.t_android;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.HashMap;

public class Show extends Fragment implements CompoundButton.OnCheckedChangeListener, BDLocationListener, AdapterView.OnItemSelectedListener {
    private TextView textViewTime, textViewDate, textViewWeek, _locationTextView;
    private Switch switchTime, switchDate, switchWeek, switchWeather1, switchWeather2, switchWeather3;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private Spinner spinner;

    private LocationClient _locationClient;
    private static final int BAIDU_READ_PHONE_STATE = 100;

    private MyDBOpenHelper myDBOpenHelper;
    private int id;
    private ContentValues values;
    private HashMap<String, TextView> textViewHashMap;
    private HashMap<String, Switch> switchHashMap;
    private HashMap<String, LinearLayout> linearLayoutHashMap;

    //判断是否为刚进去时触发onItemSelected的标志
    private boolean spinnerSelected = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show, container, false);

        textViewTime = (TextView) view.findViewById(R.id.time_label);
        textViewDate = (TextView) view.findViewById(R.id.date);
        textViewWeek = (TextView) view.findViewById(R.id.week);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.weather_today);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.weather_tomorrow);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.weather_after_tomorrow);
        _locationTextView = view.findViewById(R.id.locationTextView);
        spinner = view.findViewById(R.id.spinner1);
        values = new ContentValues();

        //根据屏幕宽度确定字体的大小
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        textViewTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150 * width / 1794);

        //绑定switch事件
        switchTime = (Switch) view.findViewById(R.id.switch_time);
        switchDate = (Switch) view.findViewById(R.id.switch_date);
        switchWeek = (Switch) view.findViewById(R.id.switch_week);
        switchWeather1 = (Switch) view.findViewById(R.id.switch_weather1);
        switchWeather2 = (Switch) view.findViewById(R.id.switch_weather2);
        switchWeather3 = (Switch) view.findViewById(R.id.switch_weather3);
        switchTime.setOnCheckedChangeListener(this);
        switchDate.setOnCheckedChangeListener(this);
        switchWeek.setOnCheckedChangeListener(this);
        switchWeather1.setOnCheckedChangeListener(this);
        switchWeather2.setOnCheckedChangeListener(this);
        switchWeather3.setOnCheckedChangeListener(this);

        _locationClient = new LocationClient(getContext());
        _locationClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        _locationClient.setLocOption(option);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        } else {
            _locationClient.start();
        }

        textViewHashMap=new HashMap<>();
        textViewHashMap.put("time", textViewTime);
        textViewHashMap.put("date", textViewDate);
        textViewHashMap.put("week", textViewWeek);
        linearLayoutHashMap=new HashMap<>();
        linearLayoutHashMap.put("firstDay", linearLayout1);
        linearLayoutHashMap.put("secondDay", linearLayout2);
        linearLayoutHashMap.put("thirdDay", linearLayout3);
        switchHashMap=new HashMap<>();
        switchHashMap.put("time", switchTime);
        switchHashMap.put("date", switchDate);
        switchHashMap.put("week", switchWeek);
        switchHashMap.put("firstDay", switchWeather1);
        switchHashMap.put("secondDay", switchWeather2);
        switchHashMap.put("thirdDay", switchWeather3);

        myDBOpenHelper = new MyDBOpenHelper(getActivity(), "clock.db", null, 1);
        SQLiteDatabase db = myDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("config", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
            values.put("time", cursor.getInt(cursor.getColumnIndex("time")));
            values.put("date", cursor.getInt(cursor.getColumnIndex("date")));
            values.put("week", cursor.getInt(cursor.getColumnIndex("week")));
            values.put("location", cursor.getInt(cursor.getColumnIndex("location")));
            values.put("firstDay", cursor.getInt(cursor.getColumnIndex("firstDay")));
            values.put("secondDay", cursor.getInt(cursor.getColumnIndex("secondDay")));
            values.put("thirdDay", cursor.getInt(cursor.getColumnIndex("thirdDay")));
            values.put("num", cursor.getInt(cursor.getColumnIndex("num")));
            setViewData();
        }

        return view;
    }

    /**
     * 绑定数据
     */
    private void setViewData() {
        for (String key : values.keySet()) {
            boolean status = (int) values.get(key) == 1;
            switch (key) {
                case "time":
                case "date":
                case "week":
                    textViewHashMap.get(key).setVisibility(status ? View.VISIBLE : View.GONE);
                    switchHashMap.get(key).setChecked(status);
                    break;
                case "firstDay":
                case "secondDay":
                case "thirdDay":
                    linearLayoutHashMap.get(key).setVisibility(status ? View.VISIBLE : View.GONE);
                    switchHashMap.get(key).setChecked(status);
                    break;
                case "num":
                    spinnerSelected = false;
                    int num = (int) values.get(key) - 1;
                    spinner.setSelection(num);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();

        SQLiteDatabase db = myDBOpenHelper.getWritableDatabase();
        db.update("config", values, "id=?", new String[]{String.valueOf(id)});
    }

    /**
     * 重写switch状态变更事件
     *
     * @param compoundButton
     * @param b
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        boolean isChecked = compoundButton.isChecked();
        switch (compoundButton.getId()) {
            case R.id.switch_time:
                textViewTime.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("time", isChecked ? 1 : 0);
                break;
            case R.id.switch_date:
                textViewDate.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("date", isChecked ? 1 : 0);
                break;
            case R.id.switch_week:
                textViewWeek.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("week", isChecked ? 1 : 0);
                break;
            case R.id.switch_weather1:
                linearLayout1.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("firstDay", isChecked ? 1 : 0);
                break;
            case R.id.switch_weather2:
                linearLayout2.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("secondDay", isChecked ? 1 : 0);
                break;
            case R.id.switch_weather3:
                linearLayout3.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                values.put("thirdDay", isChecked ? 1 : 0);
                break;
        }
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String addr = bdLocation.getAddrStr();    //获取详细地址信息
        String country = bdLocation.getCountry();    //获取国家
        String province = bdLocation.getProvince();    //获取省份
        String city = bdLocation.getCity();    //获取城市
        String district = bdLocation.getDistrict();    //获取区县
        String street = bdLocation.getStreet();    //获取街道信息
        _locationTextView.setText(city + district);
        values.put("location", city + district);
        _locationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case 1:
                BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    _locationClient.start();
                } else {
                    Log.i("tag", "onRequestPermissionsResult: " + "没有获取到权限");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (spinnerSelected) {
            values.put("num", i + 1);
        } else {
            spinnerSelected = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
