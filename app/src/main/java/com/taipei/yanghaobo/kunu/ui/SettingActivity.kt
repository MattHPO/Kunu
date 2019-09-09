package com.taipei.yanghaobo.kunu.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.taipei.yanghaobo.kunu.R

import java.util.Objects
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI

class SettingActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.kunu_setting_activity)

    Objects.requireNonNull<ActionBar>(supportActionBar).setTitle(R.string.kunu_menu_settings)

    //        setUpActionBar(navController);
  }

  //
  private fun setUpActionBar(navController: NavController) {

    NavigationUI.setupActionBarWithNavController(this, navController)
  }

  companion object {

    /**
     * @param context
     * 起點
     * @return
     * 啟動 SettingActivity 之 intent
     */
    fun newIntent(context: Context): Intent {

      return Intent(context, SettingActivity::class.java)
    }
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
