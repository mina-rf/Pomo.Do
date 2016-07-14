package com.sharif.PomoDo;


//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.Date;


/**
 * Created by mina on 7/12/16.
 */

public class NewTaskFragment extends Fragment implements View.OnClickListener{

    EditText name ;
    NumberPicker deadLineYear;
    NumberPicker deadLineMonth;
    NumberPicker deadLineDay;
    NumberPicker deadLineHour;
    NumberPicker deadLineMinute;
    EditText target;
    Spinner tagSpinner;
    EditText description;
    Button addTask;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newtask_layout, container, false);
        initilize (view);
        addTask.setOnClickListener(this);
        return view;
    }

    private void initilize(View view) {

        name = (EditText) view.findViewById(R.id.task_name);
        deadLineYear = (NumberPicker) view.findViewById(R.id.task_deadline_year);
        deadLineMonth = (NumberPicker) view.findViewById(R.id.task_deadline_month);
        deadLineDay = (NumberPicker) view.findViewById(R.id.task_deadline_day);
        deadLineHour = (NumberPicker) view.findViewById(R.id.task_deadline_hour);
        deadLineMinute  = (NumberPicker) view.findViewById(R.id.task_deadline_minute);
        target = (EditText) view.findViewById(R.id.task_target);
        tagSpinner = (Spinner) view.findViewById(R.id.task_tag);
        description = (EditText) view.findViewById(R.id.task_description);
        addTask = (Button) view.findViewById(R.id.addTask);

        deadLineYear.setMinValue(2015);
        deadLineYear.setMaxValue(2030);

        deadLineMonth.setMinValue(1);
        deadLineMonth.setMaxValue(12);

        deadLineDay.setMinValue(1);
        deadLineDay.setMaxValue(31);

        deadLineHour.setMinValue(0);
        deadLineHour.setMaxValue(23);

        deadLineMinute.setMinValue(0);
        deadLineMinute.setMaxValue(59);
    }


    @Override
    public void onClick(View view) {

        Log.d("tagggg", "onClick ");

        if (view.getId() == R.id.addTask){

            Log.d("tagggg", "onClick ");

            ToDoListFragment fragment = new ToDoListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TasksDBHelper.TASK_COLUMN_NAME , name.getText().toString());
            bundle.putString(TasksDBHelper.TASK_COLUMN_NAME , name.getText().toString());
            bundle.putString(TasksDBHelper.TASK_COLUMN_DEADLINE_YEAR , String.valueOf(deadLineYear.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DEADLINE_MONTH , String.valueOf(deadLineMonth.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DEADLINE_DAY , String.valueOf(deadLineDay.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DEADLINE_HOUR , String.valueOf(deadLineHour.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DEADLINE_MINUTE , String.valueOf(deadLineMinute.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_TARGET , String.valueOf(target.getText()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_TAG , String.valueOf(tagSpinner.getSelectedItem()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DESCRIPTION , String.valueOf(description.getText()));

            fragment.setArguments(bundle);

            //Inflate the fragment
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container , fragment);
            transaction.commit();


        }
    }
}
