package com.rarity.apps.easyexplorer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Stack<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        history = new Stack<String>();

        File fil = new File(Environment.getExternalStorageDirectory().getPath());
        Environment.get

        while(true)
        {
            if(fil.getParentFile().getPath().length() <= 1)
            {
                break;
            }
            else
            {
                fil = fil.getParentFile();
            }
        }

        addFragment(fil.getPath(), false);
    }

    public void addFragment(String path, boolean isBack){
        Fragment_Folders fragment = new Fragment_Folders();
        Bundle args = new Bundle();
        args.putString("path", path);
        fragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();

        if(!isBack)
            history.add(path);
    }

    public void showSnackBar(String msg){
        Snackbar.make(findViewById(R.id.fragment), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        history.pop();

        if(history.isEmpty()){
            super.onBackPressed();
        }else{
            addFragment(history.peek(), true);
        }
    }
}
