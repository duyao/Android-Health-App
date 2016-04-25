package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.dy.com.homework.entity.JsonUser;

/**
 * Created by dy on 2016/4/25.
 */
public class DurationFragment2 extends Fragment {
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private View vDuration;
    private String start;
    private String end;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vDuration = inflater.inflate(R.layout.fragment_duration2, container, false);

        start = this.getArguments().getString("start");
        end = this.getArguments().getString("end");
        u = this.getArguments().getParcelable("user");








        return vDuration;
    }

}
