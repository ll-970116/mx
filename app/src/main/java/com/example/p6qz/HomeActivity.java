package com.example.p6qz;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.p6qz.adapter.HomeVpAdapter;
import com.example.p6qz.fragment.HomeFragment;
import com.example.p6qz.fragment.LoveFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar_home;
    private ViewPager viewpager_home;
    private TabLayout tablayout_home;
    private RelativeLayout relativelayout_home;
    private NavigationView navigationview_home;
    private DrawerLayout drawerlayout;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initNavigationView();
    }

    private void initNavigationView() {
        View headerView = navigationview_home.getHeaderView(0);
        headerView.findViewById(R.id.imageview_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "头像", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        toolbar_home.setTitle("Toolbar");
        toolbar_home.setLogo(R.drawable.gw);
        setSupportActionBar(toolbar_home);
//旋转开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar_home, 0, 0);
        toggle.syncState();
        drawerlayout.addDrawerListener(toggle);

        //X 轴移动
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                relativelayout_home.setX(drawerView.getRight());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        //vp 数据源
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(new LoveFragment());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("我的");
        //vp 适配器
        HomeVpAdapter adapter = new HomeVpAdapter(getSupportFragmentManager(), fragments, titles);
        //绑定适配器
        viewpager_home.setAdapter(adapter);
        //实现tablayout 和viewpager 联动
        tablayout_home.setupWithViewPager(viewpager_home);
    }

    private void initView() {
        toolbar_home = (Toolbar) findViewById(R.id.toolbar_home);
        viewpager_home = (ViewPager) findViewById(R.id.viewpager_home);
        tablayout_home = (TabLayout) findViewById(R.id.tablayout_home);
        relativelayout_home = (RelativeLayout) findViewById(R.id.relativelayout_home);
        navigationview_home = (NavigationView) findViewById(R.id.navigationview_home);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        homeFragment = new HomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "列表模式");
        menu.add(0, 1, 0, "网格模式");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                homeFragment.rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                break;
            case 1:
                homeFragment.rv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 2));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}