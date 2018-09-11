package com.taipei.yanghaobo.kunu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class DogDetailActivity extends AppCompatActivity {

    private static final String TAG = "Matt" + DogDetailActivity.class.getSimpleName();

    private static final String EXTRA_KEY_DOG_ENTRY = "EXTRA_KEY_DOG_ID";

    private TextView mDogName;

    /**
     * @param context
     *          啟動的 ACT
     * @param dogEntry
     *          狗狗的資料庫PK id
     * @return
     *          啟動 DogDetailActivity 的 intent
     */
    public static Intent newIntent(Context context, DogEntry dogEntry){
        Intent intent = new Intent(context, DogDetailActivity.class);
        intent.putExtra(EXTRA_KEY_DOG_ENTRY, dogEntry);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kunu_detail_activity);

        mDogName = findViewById(R.id.dog_name_detail_text);

        Intent intent = getIntent();
        DogEntry selectedDog = intent.getParcelableExtra(EXTRA_KEY_DOG_ENTRY);

        mDogName.setText(selectedDog.getName_cn());
    }
}
