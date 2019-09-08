package com.example.app10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Help_Fragment extends Fragment {

    private TextView textView;
    private Button button;

    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.help_fragment,container,false);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
        //添加后直接退出app？？
        int backstackentry ;
        backstackentry = manager.getBackStackEntryCount();
        Log.d(getClass().getName(), String.format("value = %d", backstackentry));*/

        textView = (TextView)getActivity().findViewById(R.id.textView2);
        button = (Button)getActivity().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Fragment2", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
