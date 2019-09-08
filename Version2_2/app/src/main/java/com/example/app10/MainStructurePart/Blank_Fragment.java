package com.example.app10.MainStructurePart;

import android.content.Intent;
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
import android.widget.TextView;

import com.example.app10.CeTingPart.CeTingActivity;
import com.example.app10.LongTouchBtn;
import com.example.app10.R;
import com.triggertrap.seekarc.SeekArc;
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener;

public class Blank_Fragment extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton setting1RadioButton;
    private RadioButton setting2RadioButton;
    private RadioButton setting3RadioButton;

    private SeekArc mSeekArc;
    private TextView mSeekArcProgress;

    //预设场景的音量值
    int SETTING1 = 10;
    int SETTING2 = 20;
    int SETTING3 = 30;

        @Nullable
        @Override    //类比onCreate,
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //The savedInstanceState parameter is a Bundle that provides data about the previous instance of the fragment, if the fragment is being resumed
            View view=inflater.inflate(R.layout.blank_fragment,container,false);

            mSeekArc = (SeekArc) view.findViewById(R.id.seekArc);
            mSeekArcProgress = (TextView) view.findViewById(R.id.seekArcProgress);
            mSeekArc.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekArc seekArc) {

                }
                @Override
                public void onStartTrackingTouch(SeekArc seekArc) {

                }
                @Override
                public void onProgressChanged(SeekArc seekArc, int progress,
                                              boolean fromUser) {
                    //Log.d("Click","onProgressChanged");//没有执行到这里
                    mSeekArcProgress.setText(String.valueOf(progress));
                }
            });

            CheckBox switch0 = view.findViewById(R.id.switch_0); //开关
            radioGroup =(RadioGroup) view.findViewById(R.id.radio_group_setting);
            setting1RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_1);//场景1
            setting2RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_2);//场景2
            setting3RadioButton = (RadioButton) view.findViewById(R.id.radiobutton_3);//场景3

            //开关
            switch0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            //场景选择
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.radiobutton_1:
                            mSeekArc.setProgress(SETTING1);
                            break;
                        case R.id.radiobutton_2:
                            mSeekArc.setProgress(SETTING2);
                            break;
                        case R.id.radiobutton_3:
                            mSeekArc.setProgress(SETTING3);
                            break;
                    }
                }
            });


            LongTouchBtn button1 = (LongTouchBtn)view.findViewById(R.id.button_1);//音量增
            button1.setBackgroundResource(R.drawable.button_volumeup);
            LongTouchBtn button2 = (LongTouchBtn)view.findViewById(R.id.button_2);//音量减
            button2.setBackgroundResource(R.drawable.button_volumedown);


            //单击音量增加
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSeekArc.addProgress();
                }
            });
            //长按持续增加
            button1.setOnLongTouchListener(new LongTouchBtn.LongTouchListener() {
                @Override
                public void onLongTouch() {
                    mSeekArc.addProgress();
                }
            },500);
            //单击音量减小
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击seekbar中间的数字减小
                    mSeekArc.removeProgress();
                }
            });
            //长按持续减小
            button2.setOnLongTouchListener(new LongTouchBtn.LongTouchListener() {
                @Override
                public void onLongTouch() {
                    mSeekArc.removeProgress();
                }
            },500);

            //开始测听界面
            Button CeTingButton = (Button)view.findViewById(R.id.CeTingButton);

            CeTingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从 fragment 中切入CeTingActivity
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CeTingActivity.class);
                    startActivity(intent);

                }
            });






            return view;
        }


}
