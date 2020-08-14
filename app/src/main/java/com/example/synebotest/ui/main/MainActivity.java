package com.example.synebotest.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.synebotest.R;
import com.example.synebotest.ui.detail.DetailActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void setChosenNews(int id){
        Intent intent  = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ID_KEY,id);
        startActivity(intent);
    }
}