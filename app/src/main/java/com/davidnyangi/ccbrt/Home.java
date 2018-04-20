package com.davidnyangi.ccbrt;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.davidnyangi.ccbrt.Fragments.Activity;
import com.davidnyangi.ccbrt.Fragments.Diary;
import com.davidnyangi.ccbrt.Fragments.Homepage;
import com.davidnyangi.ccbrt.Fragments.News;
import com.davidnyangi.ccbrt.Fragments.People;
import com.davidnyangi.ccbrt.Fragments.Places;
import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    BottomBar bottomBar;
    TextView title;

    FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
//        title = (TextView) findViewById(R.id.title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
//            title.setText(getSupportActionBar().getTitle());
//            getSupportActionBar().setTitle("");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomBar = (BottomBar)findViewById(R.id.bottomBar);

        List<Fragment> fragments = new ArrayList<>(5);

        fragments.add(Activity.newInstance("PARAM1"));
        fragments.add(News.newInstance("PARAM2"));
        fragments.add(People.newInstance("PARAM3"));
        fragments.add(Diary.newInstance("PARAM4"));
        fragments.add(Places.newInstance("PARAM5"));
        bottomBar.setDefaultTab(R.id.tab_home);
        fragNavController = new FragNavController(getSupportFragmentManager(), R.id.container,fragments);
        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_notifications);
        nearby.setBadgeCount(5);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_home) {
                    Homepage homeFragment = new Homepage();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                }else if (tabId == R.id.tab_news) {
                   // fragNavController.switchTab(fragNavController.TAB1);
                }else if(tabId == R.id.tab_people){
                   // fragNavController.switchTab(fragNavController.TAB3);
                }else if(tabId == R.id.tab_diary){
                  //  fragNavController.switchTab(fragNavController.TAB4);
                }else if(tabId == R.id.tab_notifications){
//                    Intent intent = new Intent(Home.this, Locations.class);
//                    startActivity(intent);
                    //fragNavController.switchTab(fragNavController.TAB5);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_news) {
                }
            }
        });



// Remove the badge when you're done with it.
       // nearby.removeBadge();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_news, menu);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
