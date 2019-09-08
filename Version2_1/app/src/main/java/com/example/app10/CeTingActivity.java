package com.example.app10;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class CeTingActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_start;

    int index=0; //fragment的位置
    int num = 3;// fragment的数量
    FragmentManager fragmentManager;
    Fragment []ceting_fragments = new Fragment[num];
    CeTingFragment0 fragment0;
    CeTingFragment1 fragment1;
    CeTingFragment2 fragment2;

    //数字标组的对象
    private ViewGroup viewGroup;
    //用来存放数字标
    private ImageView[] tips = new ImageView[num];
    private ImageView imageView;
    //用于存放数字标id
    int []normal_resIds;
    int []selected_resIds;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceting);

        fragment0=new CeTingFragment0();
        fragment1=new CeTingFragment1();
        fragment2=new CeTingFragment2();
        ceting_fragments = new Fragment[]{fragment0,fragment1,fragment2};

       //载入碎片
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ceTing_container,ceting_fragments[index]).commit();

        //退出按钮
        Button button_exit = (Button)findViewById(R.id.button_exitCeTing);
        button_exit.setOnClickListener(this);
        //开始测听
        button_start = (Button)findViewById(R.id.button_startCeTing);
        button_start.setOnClickListener(this);

        fragment1.setMyOnClick(new CeTingFragment1.MyOnClick() {
            @Override
            public void onClick(View view) {
                index--;
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.ceTing_container,ceting_fragments[index]).commit();
                indexDecrease(index);
            }
        });

        fragment2.setMyOnClick(new CeTingFragment2.MyOnClick() {
            @Override
            public void onClick(View view) {
                index--;
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.ceTing_container,ceting_fragments[index]).commit();
                indexDecrease(index);
            }
        });

        //将数字标组放入TypedArray对象
        TypedArray ar_normal = getBaseContext().getResources().obtainTypedArray(R.array.number_normal_images);
        TypedArray ar_selected = getBaseContext().getResources().obtainTypedArray(R.array.number_selected_images);
        int len1 = ar_normal.length();
        int len2 = ar_selected.length();

        //将数字标的id保存进数组
        normal_resIds = new int[len1];
        selected_resIds = new int [len2];
        for(int i = 0;i<len1;i++){
            normal_resIds[i] = ar_normal.getResourceId(i,0); //第一个数字对应的资源不存在，则会返回defValue 0
        }
        for(int i = 0;i<len2;i++){
            selected_resIds[i] = ar_selected.getResourceId(i,0); //第一个数字对应的资源不存在，则会返回defValue 0
        }

        //数字组ViewGroup
        viewGroup = (ViewGroup)findViewById(R.id.viewGroup);
        tips = new ImageView[num];
        for(int i=0;i<num-1;i++){  //这个数组和fragment个数差1，fragment0用于载入初始界面
            imageView = new ImageView(CeTingActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(70,70));
            imageView.setPadding(20,0,20,0);
            tips[i] = imageView;
            tips[i].setBackgroundResource(normal_resIds[i]);
            if(i==0){
                tips[i].setBackgroundResource(selected_resIds[i]);
            }
            viewGroup.addView(tips[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_exitCeTing:
                Intent intent = new Intent(CeTingActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_startCeTing:
                index++;
                indexIncrease(index);
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed () {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0 ){
            getSupportFragmentManager().popBackStack();
            index--;
            indexDecrease(index);
        }
        else {
            super.onBackPressed();
        }

    }

    public void indexIncrease(int index){
        if(index==1){
            tips[index-1].setBackgroundResource(selected_resIds[index-1]);
        }
        if(index>1){
            tips[index-1].setBackgroundResource(selected_resIds[index-1]);
            tips[index-2].setBackgroundResource(normal_resIds[index-2]);
        }
        if(index<num) {
            fragmentManager.beginTransaction().replace(R.id.ceTing_container, ceting_fragments[index]).addToBackStack(null).commit();
            button_start.setText("下一步");
        }
    }
    public void indexDecrease(int index){
        if(index==0){
            button_start.setText("开始");
            tips[index].setBackgroundResource(selected_resIds[index]);
        }
        else {
            tips[index - 1].setBackgroundResource(selected_resIds[index - 1]);
            tips[index].setBackgroundResource(normal_resIds[index]);
        }

    }

}



