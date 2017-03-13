package com.example.vishnubk.sharjahcharity.ui;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vishnubk.sharjahcharity.Fragments.ItemAFragment;
import com.example.vishnubk.sharjahcharity.Fragments.ItemBFragment;
import com.example.vishnubk.sharjahcharity.Fragments.ItemFragment;
import com.example.vishnubk.sharjahcharity.LocaleHelper;
import com.example.vishnubk.sharjahcharity.R;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //ViewPager viewFlipper;
    ViewPager viewPager;
    //SwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LocaleHelper.onCreate(this, "en");
        TabLayout tabLayout = (TabLayout) findViewById( R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_coupons));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_projects));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_sms));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        viewFlipper = (ViewPager)findViewById(R.id.flipper);
//        adapter = new SwipeAdapter(this);
//        viewFlipper.setAdapter(adapter);

        //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabPagerAdapter adapter = new TabPagerAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //viewFlipper.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(viewFlipper);


    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //System.exit(0);
              //finish();exit(0);
            super.onBackPressed();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_home) {
           // viewPager.setCurrentItem(0);

            if(MainActivity.this.getClass().getSimpleName().equals("MainActivity")){
            }
            else {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_settings) {
            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate) {
            Uri uri = Uri.parse("http://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "Share app via"));
        } else if (id == R.id.nav_info) {
            //viewPager.setCurrentItem(1);
            Intent intent=new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        item.setChecked(true);
        // Set action bar title
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//Swipe tab adapter
    public class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    //Fragement for Android Tab
                    return new ItemFragment();
                case 1:
                    //Fragment for Ios Tab
                    return new ItemAFragment();
                case 2:
                    //Fragment for Windows Tab
                    return new ItemBFragment();
            }
            return null;

        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3; //No of Tabs
        }
    }

//Image slider adapter.
//    public class SwipeAdapter extends PagerAdapter {
//
//        private int[] images = {R.drawable.charity,R.drawable.charity2,R.drawable.charity3};
//        private Context ctx;
//        private LayoutInflater inflater;
//
//        public SwipeAdapter(Context ctx){
//            this.ctx = ctx;
//        }
//
//        @Override
//        public int getCount() {
//            return images.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return (view ==(LinearLayout)object);
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View v = inflater.inflate(R.layout.swipe,container,false);
//            ImageView img =(ImageView)v.findViewById(R.id.imageView);
//
//            img.setImageResource(images[position]);
//            img.setScaleType(ImageView.ScaleType.FIT_XY);
//            container.addView(v);
//            return v;
//        }
//
//        @Override
//        public void destroyItem(View container, int position, Object object) {
//            container.refreshDrawableState();
//        }
//    }

}
