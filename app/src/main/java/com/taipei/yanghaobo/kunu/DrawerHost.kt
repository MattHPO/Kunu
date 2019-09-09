package com.taipei.yanghaobo.kunu

import androidx.drawerlayout.widget.DrawerLayout

/**
 * 由 Activity 統一實作，抽換邏輯
 */
interface DrawerHost {

  val drawer: DrawerLayout
}
