package com.taipei.yanghaobo.kunu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.taipei.yanghaobo.kunu.ui.DogGridFragment;
import com.taipei.yanghaobo.kunu.ui.SettingActivity;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "Matt" + MainActivity.class.getSimpleName();

    public static final int sMAIN_ACT_REQUEST_CODE = 1;

    private DrawerLayout mKunuMainDrawer;
    private Toolbar mKunuToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kunu_main_activity);

        mKunuToolBar = findViewById(R.id.kunu_main_toolbar);
//        替代預設的 ActionBar
        setSupportActionBar(mKunuToolBar);
        ActionBar actionBar = getSupportActionBar();
//        TODO: 自定義左上角 Menu 按鍵
//        現在是複寫預設的按鈕
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.material_ic_menu);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.kunu_main_container, new DogGridFragment())
                .commit();

//       TODO: 另一種自定義左上角的方法 ActionBarDrawerToggle()

        mKunuMainDrawer = findViewById(R.id.kunu_main_drawer);
        NavigationView kunuMainNavView = findViewById(R.id.kunu_main_nav_view);
//        此為 NavigationView 中的 list items
        kunuMainNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                menuItem.setChecked(true);
                mKunuMainDrawer.closeDrawers();

//                TODO: menuItem's function
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        TODO: 增加 右上角 setting 選項
        getMenuInflater().inflate(R.menu.kunu_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    Toolbar 的 點擊事件callback
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                mKunuMainDrawer.openDrawer(Gravity.START);
                break;

            case R.id.kunu_actions_settings:
                Intent intent = SettingActivity.newIntent(this);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//    使 menu item 若為 never 下，能同時顯示 icon 與 text
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (menu != null) {
//            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
//                try {
//                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    method.setAccessible(true);
//                    method.invoke(menu, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }

    /**
     * @param fragment
     *          目的地
     * @param addToBackstack
     *          是否加入返回堆疊
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.kunu_main_container, fragment);

        if (addToBackstack){ fragmentTransaction.addToBackStack(null); }

        fragmentTransaction.commit();
    }

}
