package com.example.app10.CeTingPart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.app10.LongTouchBtn;
import com.example.app10.R;

public class CeTingFragment1 extends Fragment implements View.OnClickListener {

    private SeekBar ceTing_seekBar;
    private TextView ceTing_seekBarProgress;
    private int progress ;
    private int max;
    int addOrRemove  = 1;
    private Handler handler = new CeTingFragment1.MyHandler();
    private MyOnClick myOnClick;
    TextView textView_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ceting1,container,false);

        ceTing_seekBar = (SeekBar)view.findViewById(R.id.ceTing_SeekBar) ;
        ceTing_seekBarProgress = (TextView)view.findViewById(R.id.ceTing_seekBarProgress) ;
        max = ceTing_seekBar.getMax();

        ceTing_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ceTing_seekBarProgress.setText(String.valueOf(progress));
                CeTingFragment1.this.progress = progress;
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


        textView_back = (TextView)view.findViewById(R.id.ceTing_lastStep);
        textView_back.setOnClickListener(this);



        return view;

    }

    public interface MyOnClick{
        public void onClick(View view);
    }

    public MyOnClick getMyOnClick(){
        return myOnClick;
    }

    public void setMyOnClick(MyOnClick myOnClick){
        this.myOnClick=myOnClick;
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


            case R.id.ceTing_lastStep:
                if(myOnClick!=null){
                    myOnClick.onClick(textView_back);
                }
/*                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new CeTingFragment0();
                fm.beginTransaction().replace(R.id.ceTing_container,fragment).commit();*/
                break;
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
