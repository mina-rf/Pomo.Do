package com.sharif.PomoDo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mina on 7/9/16.
 */
public class PomodoroFragment extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button2){
            Log.d("tag", "onClick ");
            getActivity().startService(new Intent(getActivity(),TimerService.class).setAction("").putExtra("time" , 10*1000));


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.pomodoro_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(this);

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
        textView.setText(intent.getStringExtra("counter"));
    }
}
