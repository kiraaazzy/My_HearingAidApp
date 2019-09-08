package com.example.app10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Blank_Fragment extends Fragment{

    private RadioGroup radioGroup;
    private RadioButton setting1RadioButton;
    private RadioButton setting2RadioButton;
    private RadioButton setting3RadioButton;

        @Nullable
        @Override    //类比onCreate,
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //The savedInstanceState parameter is a Bundle that provides data about the previous instance of the fragment, if the fragment is being resumed
            View view=inflater.inflate(R.layout.blank_fragment,container,false);

            CheckBox switch0 = view.findViewById(R.id.switch_0); //开关
            radioGroup =(RadioGroup) view.findViewById(R.id.radio_group_setting);
            setting1RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_1);//场景1
            setting2RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_2);//场景2
            setting3RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_3);//场景3

            Button button1 = (Button)view.findViewById(R.id.button_1);//音量增
            button1.setBackgroundResource(R.drawable.button_volumeup);
            Button button2 = (Button)view.findViewById(R.id.button_2);//音量减
            button2.setBackgroundResource(R.drawable.button_volumedown);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加按钮点击事件
                }
            });//音量增加
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加按钮点击事件
                }
            });//音量减小

            switch0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                }
            });


            return view;
        }
}
