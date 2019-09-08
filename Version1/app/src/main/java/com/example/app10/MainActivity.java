package com.example.app10;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; //为什么是private

    private Blank_Fragment fragment0;
    private AboutUs_Fragment fragment1;
    private Help_Fragment fragment2;
    private Fragment[] fragments;
    private NavigationView navView;
    int lastfragment=0;


    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将Toolbar实例传入，让其外表和ActionBar一致
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        manager = getSupportFragmentManager();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initFragment();


        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,R.string.drawer_close
        ){
            public void onDrawerClosed(View view) {
                syncActionBarArrowState();
            }
            public void onDrawerOpened(View drawerView) {
                mDrawerToggle.setDrawerIndicatorEnabled(true); // drawerIndicator 是根据抽屉状态而默认展现的图标变化，即homeAsUp的变化
            }

        };
    //Navigation back icon listener
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    // 加载navigationView,并
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(changeFragment);
    //设置backstack监听器，以同步toolbar导航按钮状态
        manager.addOnBackStackChangedListener(mOnBackStackChangedListener);

    }

    //Fragment初始化
    private void initFragment() {
        fragment0 = new Blank_Fragment();
        fragment1 = new AboutUs_Fragment();
        fragment2 = new Help_Fragment();
        fragments = new Fragment[]{fragment0,fragment1,fragment2};
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,fragment0).commit();
        //transaction.addToBackStack(null);//想实现始终返回第一个fragment
    }


    //判断选择的菜单
    private NavigationView.OnNavigationItemSelectedListener changeFragment= new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.nav_AboutUs:
                    if(lastfragment!=1) {
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    break;  //break！！！！！！！！！！！！！！！！！！！！！！！！！！！！！1
                case R.id.nav_Help:
                    if(lastfragment!=2){
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    break;
                default:
                    break;
            }
           // Log.d("nav_menu_select", String.format("value = %d",lastfragment));
            mDrawerLayout.closeDrawers();
            return false;//true to display the item as the selected item
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private FragmentManager.OnBackStackChangedListener
            mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            syncActionBarArrowState();
        }
    };


    @Override
    public void onBackPressed () {  //按返回键回到上一级而不是退出
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }



    @Override
    protected void onDestroy() {
        manager.removeOnBackStackChangedListener(mOnBackStackChangedListener);
        super.onDestroy();
    }



    private void syncActionBarArrowState() {
        int backStackEntryCount = manager.getBackStackEntryCount();
        mDrawerToggle.setDrawerIndicatorEnabled(backStackEntryCount == 0);
    }



    public boolean onCreateOptionsMenu(Menu menu) {  //创建menu菜单项目
        getMenuInflater().inflate(R.menu.toolbar, menu); //加载了toolbar.xml
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){    //处理菜单被选中的运行事件
/*        switch (item.getItemId()) {
            case android.R.id.home: //HomeAsUp
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }*/
        if (mDrawerToggle.isDrawerIndicatorEnabled() &&
                mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        } else if (item.getItemId() == android.R.id.home &&
                manager.popBackStackImmediate()) //popBackStackImmediate()是同步方法，popBackStack是异步方法，仅将出栈这个动作加到了消息队列顶部
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
       // transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(!fragments[index].isAdded()){
            transaction.replace(R.id.fragment_container,fragments[index]).addToBackStack(null).commit();
        }
       // transaction.commit();
      //transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}
