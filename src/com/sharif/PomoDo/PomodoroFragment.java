package com.sharif.PomoDo;

import android.content.*;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mina on 7/9/16.
 */
public class PomodoroFragment extends Fragment implements View.OnClickListener {
    Button button;
    TimerView timerView;
    long total = 1 * 60 * 1000;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button2){
            Log.d("tag", "onClick ");
            getActivity().startService(new Intent(getActivity(),TimerService.class).setAction("").putExtra("time" , total));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.pomodoro_layout, container, false);
        button = (Button) view.findViewById(R.id.button2);
        timerView = (TimerView) view.findViewById(R.id.timeView);
        button.setOnClickListener(this);
        timerView.setAngle(0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int time = preferences.getInt("work_interval",9);
        Log.d("time =", ""+time);
        total = time*60*1000*5;
        timerView.setTime(getTime(total));
        timerView.invalidate();
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter(TimerService.BROADCAST_TIME));

        return view;

    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    private void updateUI(Intent intent) {
        long l = intent.getLongExtra("counter" , 0);
        int angle =360 - (int) (l * 360 / total);
        timerView.setAngle(angle);
        timerView.setTime(getTime(l));
        timerView.invalidate();
    }

    public static String getTime(long time){
        int min = (int)(time/(1000*60));
        int second = (int)((time % (60 * 1000))/1000);
        String output = String.format("%02d:%02d",min ,  second);
        return output;
    }
}
