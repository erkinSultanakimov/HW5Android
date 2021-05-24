package com.geekchtech.hw5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.geekchtech.hw5.databinding.ActivityMainBinding;
import com.geekchtech.hw5.fragments.DashFragment;
import com.geekchtech.hw5.fragments.HomeFragment;
import com.geekchtech.hw5.fragments.NotifFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private DashFragment dashFragment = new DashFragment();
    private NotifFragment notifFragment = new NotifFragment();
    private FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.bottomNav.setOnNavigationItemSelectedListener(listener);
        fm.beginTransaction().add(R.id.fm_container, notifFragment,"notif").hide(notifFragment).commit();
        fm.beginTransaction().add(R.id.fm_container,dashFragment,"Dash").hide(dashFragment).commit();
        fm.beginTransaction().add(R.id.fm_container,homeFragment).commit();

    }

   private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
       switch (item.getItemId()){
           case R.id.home_fragment:
               fm.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out).hide(active).show(homeFragment).commit();
               binding.tvTitle.setText("Home");
               active = homeFragment;
               return true;

           case R.id.dashboard_fragment:
               fm.beginTransaction().hide(active).show(dashFragment).commit();
               binding.tvTitle.setText("Dashboard");
               active = dashFragment;
               return true;
           case R.id.notification_fragment:
               fm.beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.fade_out).hide(active).show(notifFragment).commit();
               binding.tvTitle.setText("Notification");
               active = notifFragment;
               return true;}


       return false;
   };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings){
          startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

   public void hideBottomNav(){
        binding.bottomNav.setVisibility(View.GONE);
    }

    public void visibleBottomNav(){
        binding.bottomNav.setVisibility(View.VISIBLE);
    }

    public void hideToolbar(){
        binding.toolbar.setVisibility(View.GONE);
    }
}