package com.taipei.yanghaobo.kunu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), DrawerHost {
  override lateinit var drawer: DrawerLayout
    private set

  private var mKunuMainBottomNav: BottomNavigationView? = null

  /**
   *
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.kunu_main_activity)

    Log.d("Matt!", "before handler")

    Handler().post {
      try {
        Log.d("Matt!", "sleep")
        Thread.sleep(4000)
        Log.d("Matt!", "sleep end")
      } catch (e: InterruptedException) {
        e.printStackTrace()
      }
    }

    Log.d("Matt!", "onCreate: $this")

    drawer = findViewById(R.id.kunu_main_drawer)

    val navHostFragment = supportFragmentManager
      .findFragmentById(R.id.kunu_main_nav_host_fragment) as NavHostFragment?
    var navController: NavController? = null
    if (navHostFragment != null) {
      navController = navHostFragment.navController
    }

    //        setUpActionBar(navController);

    setUpBottomNav(navController!!)

    setUpNav(navController)
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
    //        mKunuMainBottomNav.getMenu()
    //                .findItem(R.id.nav_favorite_frag)
    //                .getIcon()
    //                .setColorFilter(R.color.colorPrimary, PorterDuff.Mode.SRC_ATOP);
    //        mKunuMainBottomNav.getMenu()
    //                .findItem(R.id.nav_favorite_frag)
    //                .getIcon()
    //                .setTintMode(PorterDuff.Mode.SRC_IN);

  }

  /**
   * 設定直立畫面的底部Nav
   *
   * @param navController 導覽控制器
   */
  private fun setUpNav(navController: NavController) {
    val kunuMainNavView = findViewById<NavigationView>(R.id.kunu_main_nav_view)
    if (kunuMainNavView != null) {
      NavigationUI.setupWithNavController(kunuMainNavView, navController)
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
   * @param navController 導覽控制器
   */
  private fun setUpBottomNav(navController: NavController) {
    mKunuMainBottomNav = findViewById(R.id.kunu_main_bottom_nav)
    if (mKunuMainBottomNav != null) {
      NavigationUI.setupWithNavController(mKunuMainBottomNav!!, navController)
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
   * @return Android Jetpack's navigation
   */
  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI
      .navigateUp(
        Navigation.findNavController(this, R.id.kunu_main_nav_host_fragment),
        drawer
      )
  }

  override fun onStart() {
    super.onStart()
    Log.d("Matt!", "onStart: $this")
  }

  override fun onResume() {
    super.onResume()
    Log.d("Matt!", "onResume: $this")
  }

  override fun onPause() {
    super.onPause()
    Log.d("Matt!", "onPause: $this")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("Matt!", "onDestroy: $this")
  }

  companion object {

    val sMAIN_ACT_REQUEST_CODE = 1

    fun newIntent(context: Context): Intent {

      return Intent(context, MainActivity::class.java)
    }
  }
}
