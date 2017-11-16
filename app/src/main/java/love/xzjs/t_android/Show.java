package love.xzjs.t_android;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class Show extends Fragment {
    public String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_show, container, false);
        TextView textView = view.findViewById(R.id.time_label);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 150 * width / 1794);
        return view;
    }
}
