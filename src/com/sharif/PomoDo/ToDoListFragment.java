package com.sharif.PomoDo;

import android.app.ActionBar;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mina on 7/9/16.
 */
public class ToDoListFragment  extends Fragment implements View.OnClickListener{

    ListView taskList ;
    EditText mEditTest;

    List<String> list = new ArrayList<String>();
    CustomAdapter adapter;

    TasksDBHelper tasksDB ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view;
        view= inflater.inflate(R.layout.todolist_layout, container, false);

        tasksDB = new TasksDBHelper(getActivity());
        taskList = (ListView) view.findViewById(R.id.tasks_listView);
        mEditTest = (EditText) view.findViewById((R.id.editText));

        adapter = new CustomAdapter(getActivity(), list);


        Button addButton = (Button) view.findViewById(R.id.button);
        addButton.setOnClickListener(this);

        if (getArguments() != null) {
            String value = getArguments().getString("taskName");
//            taskList.addView(createNewTask(value));
            tasksDB.insertTask(value , "" , "" , "" , "", "" , "", "" , "", "" , 10 ,0);
            Cursor c = tasksDB.getPerson(value);
            if (value != null)
                Log.d("value", value);
        }

        //draw Views
        Cursor c = tasksDB.getAllPersons();
        if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {
                String name = c.getString(c
                        .getColumnIndex(TasksDBHelper.TASK_COLUMN_NAME));


                list.add(name);
                adapter.notifyDataSetChanged();

                c.moveToNext();
            }
        }


        taskList.setAdapter(adapter);
        this.setRetainInstance(true);
        return view;

    }

    public void addTask(View view){


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    private LinearLayout createNewTask (String text){
        ActionBar.LayoutParams lparams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(lparams);
        textView.setText("task: " + text);



        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(lparams);
        layout.addView(textView);

        return layout;
    }
    @Override
    public void onClick(View view) {

        Log.d("inam tag!", "onClick joz button");
        if (view.getId() == R.id.button){

//
            Log.d("inam tag!", "onClick ");
            NewTaskFragment newFragment = new NewTaskFragment();

//            tasksDB.insertTask(mEditTest.getText().toString() , "" , "" , "" , "", "" , "", "" , "", "" , 10 ,0);
//            Cursor c = tasksDB.getPerson(mEditTest.getText().toString());
//            if(c != null && c.moveToFirst()) {
//                Log.d("cursor man : ", c.getString(c.getColumnIndex(TasksDBHelper.TASK_COLUMN_NAME)));
//            }

            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }

    }
}
