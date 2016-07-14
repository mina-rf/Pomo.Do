package com.sharif.PomoDo;


//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mina on 7/12/16.
 */

public class NewTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener ,View.OnClickListener {

    EditText name ;
    NumberPicker deadLineYear;
    NumberPicker deadLineMonth;
    NumberPicker deadLineDay;
    NumberPicker deadLineHour;
    NumberPicker deadLineMinute;
    NumberPicker target;
    Spinner tagSpinner;
    EditText description;
    Button addTask;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newtask_layout, container, false);
        initialize(view);
        addTask.setOnClickListener(this);
        return view;

    }

    private void initialize(View view) {

        name = (EditText) view.findViewById(R.id.task_name);
        deadLineYear = (NumberPicker) view.findViewById(R.id.task_deadline_year);
        deadLineMonth = (NumberPicker) view.findViewById(R.id.task_deadline_month);
        deadLineDay = (NumberPicker) view.findViewById(R.id.task_deadline_day);
        deadLineHour = (NumberPicker) view.findViewById(R.id.task_deadline_hour);
        deadLineMinute  = (NumberPicker) view.findViewById(R.id.task_deadline_minute);
        target = (NumberPicker) view.findViewById(R.id.task_target);
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

        target.setMinValue(1);
        target.setMaxValue(10);


        List<String> colors = new ArrayList<String>();
        colors.add("RED");
        colors.add("ORANGE");
        colors.add("YELLOW");
        colors.add("GREEN");
        colors.add("BLUE");
        colors.add("PINK");

        tagSpinner.setOnItemSelectedListener(this);
        MyArrayAdapter adapter = new MyArrayAdapter(getActivity() ,colors);
        tagSpinner.setAdapter(adapter);

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
            bundle.putString(TasksDBHelper.TASK_COLUMN_TARGET , String.valueOf(target.getValue()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_TAG , String.valueOf(tagSpinner.getSelectedItem()));
            bundle.putString(TasksDBHelper.TASK_COLUMN_DESCRIPTION , String.valueOf(description.getText()));

            fragment.setArguments(bundle);

            //Inflate the fragment
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container , fragment);
            transaction.commit();


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static int getColor (String color){

        if (color.equals("RED"))
            return Color.parseColor("#DC143C");
        if (color.equals("ORANGE"))
            return Color.parseColor("#FF8C00");
        if (color.equals("YELLOW"))
            return Color.parseColor("#FFD700");
        if (color.equals("GREEN"))
            return Color.parseColor("#32CD32");
        if (color.equals("BLUE"))
            return Color.parseColor("#00BFFF");
        if (color.equals("PINK"))
            return Color.parseColor("#FF69B4");

        return Color.BLACK;

    }


    private class MyArrayAdapter extends BaseAdapter {



        Context context;
        List<String> colors;
        LayoutInflater inflater;

        public MyArrayAdapter(Context applicationContext, List<String> colors) {
            this.context = applicationContext;
            this.colors = colors;
            inflater = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return colors.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.tag , null);
            TextView tv = (TextView) view.findViewById(R.id.color_name);
            tv.setTextColor(Color.WHITE);
            tv.setText(colors.get(i));
//            view.setMinimumWidth(30);
            view.setBackgroundColor(getColor(colors.get(i)));
            return view;
        }



    }
}


