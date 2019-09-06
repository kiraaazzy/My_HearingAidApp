package com.example.app10;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class CeTingFragment0 extends Fragment implements View.OnClickListener {

    private SeekBar ceTing_seekBar;
    private TextView ceTing_seekBarProgress;
    private int progress ;
    private int max;
    int addOrRemove  = 1;
    private Handler handler = new CeTingFragment0.MyHandler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ceting0,container,false);

        ceTing_seekBar = (SeekBar)view.findViewById(R.id.ceTing_SeekBar) ;
        ceTing_seekBarProgress = (TextView)view.findViewById(R.id.ceTing_seekBarProgress) ;
        max = ceTing_seekBar.getMax();

        ceTing_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ceTing_seekBarProgress.setText(String.valueOf(progress));
                CeTingFragment0.this.progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //调节音量
        LongTouchBtn button_1 = (LongTouchBtn)view.findViewById(R.id.ceTing_button_VolunmPlus);
        LongTouchBtn button_2 = (LongTouchBtn)view.findViewById(R.id.ceTing_button_VolunmMinus);

        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);

        button_1.setOnLongTouchListener(new LongTouchBtn.LongTouchListener() {

            @Override
            public void onLongTouch() {
                addProgress();
            }
        },500);
        button_2.setOnLongTouchListener(new LongTouchBtn.LongTouchListener() {

            @Override
            public void onLongTouch() {
                removeProgress();
            }
        },500);




        
        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ceTing_button_VolunmPlus:
                addProgress();
                break;

            case R.id.ceTing_button_VolunmMinus:
                removeProgress();
                break;

/*            case R.id.button_exitCeTing:
                Intent intent = new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
                break;*/
/*            case R.id.button_startCeTing:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new CeTingFragment1();
                fm.beginTransaction().replace(R.id.ceTing_container,fragment).commit();*/


            default:

                break;
        }
    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    ceTing_seekBarProgress.setText(String.valueOf(progress));
                    ceTing_seekBar.setProgress(progress);
                    break;
                default:
                    break;

            }
        }
    }



    public void addProgress(){
        if(progress<max) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    message.what = 0;//用于区分不同的message
                    progress += addOrRemove;
                    bundle.putInt("Key", progress);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }).start();
        }
    }

    public void removeProgress(){
        if(progress>0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    message.what = 0;//用于区分不同的message
                    progress -= addOrRemove;
                    bundle.putInt("Key", progress);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }).start();
        }
    }
}
