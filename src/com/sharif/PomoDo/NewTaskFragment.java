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


/**
 * Created by mina on 7/12/16.
 */

public class NewTaskFragment extends Fragment implements View.OnClickListener{

    EditText textView;
    Button okButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newtask_layout, container, false);
        Log.d("tagggg", "onClick :((( ");
        textView = (EditText) view.findViewById(R.id.task_name);
        okButton = (Button) view.findViewById(R.id.okkk);
        okButton.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {

        Log.d("tagggg", "onClick ");

        if (view.getId() == R.id.okkk){

            Log.d("tagggg", "onClick ");
            ToDoListFragment f = new ToDoListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("taskName" , textView.getText().toString());

            f.setArguments(bundle);

                //Inflate the fragment
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container , f);
            transaction.commit();


        }
    }
}
