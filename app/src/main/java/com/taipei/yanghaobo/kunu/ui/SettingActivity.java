package com.taipei.yanghaobo.kunu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.taipei.yanghaobo.kunu.R;

public class SettingActivity extends AppCompatActivity {

    /**
     * @param context
     *         起點
     * @return
     *         啟動 SettingActivity 之 intent
     */
    public static Intent newIntent(Context context) {
        //noinspection UnnecessaryLocalVariable
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kunu_setting_activity);

//        TODO: TEST
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.kunu_settings_container, new SettingFragment()).
                commit();
    }
}
