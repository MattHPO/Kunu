package com.taipei.yanghaobo.kunu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.taipei.yanghaobo.kunu.R;

import java.util.Objects;

import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

public class SettingActivity extends AppCompatActivity {

    /**
     * @param context
     *         起點
     * @return
     *         啟動 SettingActivity 之 intent
     */
    @NonNull
    public static Intent newIntent(Context context) {
        //noinspection UnnecessaryLocalVariable
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kunu_setting_activity);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.kunu_menu_settings);

//        setUpActionBar(navController);
    }
//
    private void setUpActionBar(@NonNull NavController navController) {

        NavigationUI.setupActionBarWithNavController(this, navController);
    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        Navigation.findNavController(this, R.id.kunu_settings_nav_host_fragment).navigate(R.id.action_settingFragment_to_mainActivity2);
////        FIXME: 權宜之計，無法使用 Navigation class 進行返回堆疊的控制
//        ActivityCompat.finishAffinity(this);
//        return true;
//    }

}
