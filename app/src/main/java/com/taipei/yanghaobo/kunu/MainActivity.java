package com.taipei.yanghaobo.kunu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements DrawerHost {

    private static final String TAG = "Matt " + MainActivity.class.getSimpleName();

    public static final int sMAIN_ACT_REQUEST_CODE = 1;
    private DrawerLayout mKunuMainDrawer;

    private static Context sContext;

    @NonNull
    public static Intent newIntent(Context context) {
        //noinspection UnnecessaryLocalVariable
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kunu_main_activity);

        mKunuMainDrawer = findViewById(R.id.kunu_main_drawer);

        sContext = getApplicationContext();


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.kunu_main_nav_host_fragment);
        NavController navController = null;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

//        setUpActionBar(navController);

        setUpBottomNav(navController);

        setUpNav(navController);
//        此為 NavigationView 中的 list items
//        if (kunuMainNavView != null){
//            kunuMainNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                    menuItem.setChecked(true);
//                    mKunuMainDrawer.closeDrawers();
//
////                TODO: menuItem's function
//                    return true;
//                }
//            });
//        }
    }

    /**
     * 設定直立畫面的底部Nav
     *
     * @param navController
     *          導覽控制器
     */
    private void setUpNav(@NonNull NavController navController) {
        NavigationView kunuMainNavView = findViewById(R.id.kunu_main_nav_view);
        if (kunuMainNavView != null){
            NavigationUI.setupWithNavController(kunuMainNavView, navController);
//            kunuMainNavView.setNavigationItemSelectedListener(menuItem -> {
//                switch (menuItem.getItemId()) {
//
//                    case R.id.action_global_dogGridFragment2:
//                        kunuMainNavView.setCheckedItem(menuItem);
//                        break;
//                }
//                return false;
//            });
        }
    }
    /**
     * 設定直立畫面的底部Nav
     *
     * @param navController
     *          導覽控制器
     */
    private void setUpBottomNav(@NonNull NavController navController) {
        BottomNavigationView kunuMainBottomNav = findViewById(R.id.kunu_main_bottom_nav);
        if (kunuMainBottomNav != null){
            NavigationUI.setupWithNavController(kunuMainBottomNav, navController);
        }
    }
//    /**
//     * 設定Action Bar
//     *
//     * @param navController
//     *          導覽控制器
//     */
//    private void setUpActionBar(NavController navController) {
//
//        Toolbar toolbar = findViewById(R.id.kunu_main_toolbar);
//        setSupportActionBar(toolbar);
//
//        if (mKunuMainDrawer != null){
//            NavigationUI.setupActionBarWithNavController(this, navController, mKunuMainDrawer);
//        }
//    }

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

//    /**
//     * 切換 FrameLayout 內容
//     *
//     * @param fragment
//     *          目的地
//     * @param addToBackstack
//     *          是否加入返回堆疊
//     */
//    @Override
//    public void navigateTo(Fragment fragment, boolean addToBackstack) {
//
//        FragmentTransaction fragmentTransaction =
//                getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.kunu_main_nav_host_fragment, fragment);
//
//        if (addToBackstack){ fragmentTransaction.addToBackStack(null); }
//
//        fragmentTransaction.commit();
//    }

    /**
     * 當左上角Up button被點擊時的 callback
     *
     * @return
     *  Android Jetpack's navigation
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.kunu_main_nav_host_fragment),
                mKunuMainDrawer);
    }

    @Override
    public DrawerLayout getDrawer() {
        return mKunuMainDrawer;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
