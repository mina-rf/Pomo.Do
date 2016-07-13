package com.sharif.PomoDo;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mina on 7/9/16.
 */
public class ToDoListFragment  extends Fragment implements View.OnClickListener{

    LinearLayout taskList ;
    EditText mEditTest;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.todolist_layout, container, false);
        Log.d("tag", "onCreateView fragment ");
        taskList = (LinearLayout) view.findViewById(R.id.taskList);
        mEditTest = (EditText) view.findViewById((R.id.editText));

        Button addButton = (Button) view.findViewById(R.id.button);
        addButton.setOnClickListener(this);
        Log.d("tag : ", "onCreateView ");
        if (getArguments() != null) {
            String value = getArguments().getString("taskName");
            taskList.addView(createNewTask(value));
            if (value != null)
                Log.d("value", value);
        }
        return view;

    }

    public void addTask(View view){


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
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();


        }

    }
}
