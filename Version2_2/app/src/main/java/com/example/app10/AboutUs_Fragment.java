package com.example.app10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUs_Fragment extends Fragment {

    private TextView textView;
    private Button button;


    @Nullable
    @Override    //类比onCreate,
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //The savedInstanceState parameter is a Bundle that provides data about the previous instance of the fragment, if the fragment is being resumed
        View view=inflater.inflate(R.layout.aboutus_fragment,container,false);

        //与父容器交互的时候可能用到？
   /*     ViewGroup parent=(ViewGroup)view.getParent();
        if(parent!=null) parent.removeView(view);*/


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView)getActivity().findViewById(R.id.textView1);
        button = (Button)getActivity().findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Fragment1", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
