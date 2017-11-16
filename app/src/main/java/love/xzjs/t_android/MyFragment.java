package love.xzjs.t_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MyFragment extends android.support.v4.app.Fragment {
    public String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show,container,false);
        Log.e("HEHE",content);
        return view;
    }
}
