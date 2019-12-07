package com.hujiang.mytest;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.hujiang.mytest.fragment.AfterFollowFragment;
import com.hujiang.mytest.fragment.ConstraintTestFragment;
import com.hujiang.mytest.fragment.CustomDrawableFragment;
import com.hujiang.mytest.fragment.CustomViewDrawTextBaseLineFragment;
import com.hujiang.mytest.fragment.GSONLearnFragment;
import com.hujiang.mytest.fragment.HandlerLearnFragment;
import com.hujiang.mytest.fragment.HookFragment;
import com.hujiang.mytest.fragment.JNIFragment;
import com.hujiang.mytest.fragment.SlidingConflictsFragment;
import com.hujiang.mytest.fragment.SpannableStringFragment;
import com.hujiang.mytest.fragment.ThreadLocalFragment;
import com.hujiang.mytest.fragment.ThreadPoolFragment;
import com.hujiang.mytest.fragment.TransparentCircleFragment;
import com.hujiang.mytest.fragment.ViewAnimationFragment;
import com.hujiang.mytest.fragment.aidlFragment.AidlFragment;
import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.drag.helper.fragment.LearnViewDragHelperFragment;
import com.hujiang.mytest.fragment.dynamic.animation.DynamicAnimationFragment;
import com.hujiang.mytest.fragment.fixHeigth.FixHeightRecyclerViewFragment;
import com.hujiang.mytest.fragment.hencoder.draw1.CustomViewFragment1;
import com.hujiang.mytest.fragment.hencoder.draw2.CustomViewFragment2;
import com.hujiang.mytest.fragment.hencoder.draw4.PracticeDraw4;
import com.hujiang.mytest.fragment.jet.pack.JetPackTestFragment;
import com.hujiang.mytest.fragment.lock.JavaLockFragment;
import com.hujiang.mytest.fragment.manager.TaskManagerFragment;
import com.hujiang.mytest.fragment.open.udid.fragment.OpenUDIDFragment;
import com.hujiang.mytest.fragment.reflect.ReflectTestFragment;
import com.hujiang.mytest.fragment.test.kotlin.KotlinLearnFragment;
import com.hujiang.mytest.fragment.transition.TransitionFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView (R.id.toolbar)
    Toolbar mToolbar;
    @BindView (R.id.dl_main_drawer)
    DrawerLayout mDrawerLayout;
    @BindView (R.id.viewpager)
    ViewPager mViewPager;
    @BindView (R.id.tabs)
    TabLayout mTabLayout;
    LinkedHashMap<String, Fragment> mFragments = new LinkedHashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActionBar();
        initViewPagerAndTipView();
        setupDrawerContent();
        Log.i("info", getApplicationContext().getDir("cache", 0).getPath());
        Log.i("info", getApplicationContext().getCacheDir().getPath());
        Log.i("info", getApplicationContext().getExternalCacheDir().getPath());
    }



    private void initFragment() {
        mFragments.put("java Lock", new JavaLockFragment());
        mFragments.put("JAVA 反射", new ReflectTestFragment());
        mFragments.put("gson 源码debug", new GSONLearnFragment());
        mFragments.put("transitionFragment", new TransitionFragment());
        mFragments.put("DynamicAnimation", new DynamicAnimationFragment());
        mFragments.put("jetPack", new JetPackTestFragment());
        mFragments.put("task_manager", new TaskManagerFragment());
        mFragments.put("kotlin_learn", KotlinLearnFragment.Companion.newInstance("kotlin"));
        mFragments.put("view_animation", new ViewAnimationFragment());
        mFragments.put("ThreadPool", new ThreadPoolFragment());
        mFragments.put("CustomView1_4", new PracticeDraw4());

        mFragments.put("CustomView1_2", new CustomViewFragment2());
        mFragments.put("CustomView1_1", new CustomViewFragment1());
        mFragments.put("constraint_test", new ConstraintTestFragment());

        mFragments.put("recyclerViewFixHeigth", new FixHeightRecyclerViewFragment());
        mFragments.put("jni", new JNIFragment());
        mFragments.put("Paint 文字居中", new CustomViewDrawTextBaseLineFragment());
        mFragments.put("learnDragHelperView", new LearnViewDragHelperFragment());

        mFragments.put("openUDIDFragemnt", new OpenUDIDFragment());
        mFragments.put("hook", new HookFragment());

        //晚上增加一个属性动画
        mFragments.put("尾部跟随", new AfterFollowFragment());
        mFragments.put("handler", new HandlerLearnFragment());
        mFragments.put("thread_local", new ThreadLocalFragment());
        mFragments.put("custom_drawable", new CustomDrawableFragment());
        mFragments.put("aidl", new AidlFragment());
        mFragments.put("transparentCircle", new TransparentCircleFragment());
        mFragments.put("spannablestring", new SpannableStringFragment());
        mFragments.put("滑动冲突处理", new SlidingConflictsFragment());

        mFragments.put("Page Two", new ListFragment());


        for (String _title : mFragments.keySet()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(_title));
        }
    }

    private void initViewPagerAndTipView() {
        //另一个值 ： MODE_FIXED
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initFragment();
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), new ArrayList<>(mFragments.values()), new ArrayList<>(mFragments.keySet()));
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }


    private void initActionBar() {
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent() {
        NavigationView _navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        if (null == _navigationView) return;
        _navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
