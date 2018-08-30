package com.taipei.yanghaobo.kunu.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MainActivityVM mMainActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.dog_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainVMFactory factory = InjectorUtils.provideMainVMFactory(this);
        MainPagedAdapter pagedAdapter = new MainPagedAdapter(this);
        mMainActivityVM = ViewModelProviders.of(this, factory).get(MainActivityVM.class);
        mMainActivityVM.getDogList().observe(this, new Observer<PagedList<DogEntry>>() {
            @Override
            public void onChanged(@Nullable PagedList<DogEntry> dogEntries) {
                pagedAdapter.submitList(dogEntries);
            }
        });
    }
}
