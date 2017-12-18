package love.xzjs.t_android;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


public class Show extends Fragment implements CompoundButton.OnCheckedChangeListener, BDLocationListener {
    private TextView textViewTime, textViewDate, textViewWeek,_locationTextView;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3;

    private LocationClient _locationClient;
    private static final int BAIDU_READ_PHONE_STATE =100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show, container, false);

        textViewTime = (TextView) view.findViewById(R.id.time_label);
        textViewDate = (TextView)view.findViewById(R.id.date);
        textViewWeek = (TextView)view.findViewById(R.id.week);
        linearLayout1=(LinearLayout)view.findViewById(R.id.weather_today);
        linearLayout2=(LinearLayout)view.findViewById(R.id.weather_tomorrow);
        linearLayout3=(LinearLayout)view.findViewById(R.id.weather_after_tomorrow);
        _locationTextView=view.findViewById(R.id.locationTextView);

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

        _locationClient=new LocationClient(getContext());
        _locationClient.registerLocationListener(this);
        LocationClientOption option=new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        _locationClient.setLocOption(option);

        if(ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            requestPermissions( new String[]{ Manifest.permission.READ_PHONE_STATE },BAIDU_READ_PHONE_STATE );
        }else{
            _locationClient.start();
        }

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

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String addr = bdLocation.getAddrStr();    //获取详细地址信息
        String country = bdLocation.getCountry();    //获取国家
        String province = bdLocation.getProvince();    //获取省份
        String city = bdLocation.getCity();    //获取城市
        String district = bdLocation.getDistrict();    //获取区县
        String street = bdLocation.getStreet();    //获取街道信息
        _locationTextView.setText(city+district);
        _locationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
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
}
