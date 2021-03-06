package com.sharif.PomoDo;

import android.app.ActionBar;
import android.database.Cursor;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mina on 7/9/16.
 */
public class ToDoListFragment  extends Fragment implements View.OnClickListener {

    ListView taskList ;
    EditText mEditTest;

    List<TaskHolder> list = new ArrayList<TaskHolder>();
    CustomAdapter adapter;

    TasksDBHelper tasksDB ;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d("context menu", "onCreateContextMenu ");
//        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0 , v.getId(),0,R.string.delete_task);
        Log.d("context menu", "onCreateContextMenu ");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info  =  (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle().equals((getActivity().getString(R.string.delete_task)))){
            return true;
        }
        else if (item.getTitle().equals((getActivity().getString(R.string.edit_task)))){


            return true;

        }
        else
            return super.onContextItemSelected(item);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view;
        view= inflater.inflate(R.layout.todolist_layout, container, false);

        
        tasksDB = new TasksDBHelper(getActivity());
        taskList = (ListView) view.findViewById(R.id.tasks_listView);
        adapter = new CustomAdapter(getActivity(), list, new CustomAdapter.ListAdapterListener() {
            @Override
            public void onClickAtOKButton(int position) {
                changePager();
            }
        });

        Button addButton = (Button) view.findViewById(R.id.button);
        addButton.setOnClickListener(this);
        Drawable drawable = view.getResources().getDrawable(R.drawable.add);
        drawable.setBounds(0, 0, 150 ,
                170);
        addButton.setCompoundDrawables(null,drawable, null, null); //set drawab
//        addButton.setFocusable(true);

        if (getArguments() != null) {
            addNewTaskToDB(getArguments());
        }

        Log.d("seda shod!", "onCreateView ");
        //draw Views
        list.removeAll(list);
        Cursor c = tasksDB.getAllPersons();
        if (c .moveToFirst()) {

            while (c.isAfterLast() == false) {

                TaskHolder newTask = new TaskHolder();
                newTask.taskName =c.getString(c.getColumnIndex(TasksDBHelper.TASK_COLUMN_NAME));
                newTask.target = c.getInt(c.getColumnIndex(TasksDBHelper.TASK_COLUMN_TARGET));
                newTask.done = c.getInt(c.getColumnIndex(TasksDBHelper.TASK_COLUMN_DONE));
                newTask.color = c.getInt(c.getColumnIndex(TasksDBHelper.TASK_COLUMN_TAG));
                list.add(newTask);
                adapter.notifyDataSetChanged();

                c.moveToNext();
            }
        }


        taskList.setAdapter(adapter);
        this.setRetainInstance(true);

        taskList.setClickable(true);
        registerForContextMenu(taskList);
//        taskList.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            public void onCreateContextMenu(ContextMenu menu, View v,
//                                            ContextMenu.ContextMenuInfo menuInfo) {
//                menu.add(0, v.getId(), 1, "Add");
//                menu.add(0, v.getId() , 2,"Remove");
//            }});


        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("on item long click listener");
                return false;
            }
        });
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("item clicked!");
            }
        });

        return view;

    }

    private void changePager() {
        ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(0);

    }

    private void addNewTaskToDB(Bundle arguments) {

        tasksDB.insertTask(arguments.getString(TasksDBHelper.TASK_COLUMN_NAME),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_DEADLINE_YEAR),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_DEADLINE_MONTH),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_DEADLINE_DAY),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_DEADLINE_HOUR),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_DEADLINE_MINUTE),
                arguments.getString(TasksDBHelper.TASK_COLUMN_DESCRIPTION),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_TAG),
                arguments.getInt(TasksDBHelper.TASK_COLUMN_TARGET),
                0);

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

            Log.d("inam tag!", "onClick ");
            NewTaskFragment newFragment = new NewTaskFragment();

            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
        if (view.getId() == R.id.tasks_listView){
            System.out.println("heeereee");
        }

    }
}
