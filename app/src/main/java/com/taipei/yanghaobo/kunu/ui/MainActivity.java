package com.taipei.yanghaobo.kunu.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainVMFactory factory = InjectorUtils.provideMainVMFactory(this);
        final MainPagedAdapter pagedAdapter = new MainPagedAdapter(this);
        mRecyclerView.setAdapter(pagedAdapter);

        mMainActivityVM = ViewModelProviders.of(this, factory).get(MainActivityVM.class);
        mMainActivityVM.getDogList().observe(this, new Observer<PagedList<DogEntry>>() {
            @Override
            public void onChanged(@Nullable PagedList<DogEntry> dogEntries) {
                pagedAdapter.submitList(dogEntries);
//                pagedAdapter.notifyDataSetChanged();
                PagedList<DogEntry> currentList = pagedAdapter.getCurrentList();
                Toast.makeText(MainActivity.this,
                        "狗狗列表長度為：" + dogEntries.size() + "\n" + "現在present List 長度為：" + currentList.size(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });


    }
}
