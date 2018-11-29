package com.taipei.yanghaobo.kunu;

import android.support.v4.widget.DrawerLayout;

/**
 *  由 Activity 統一實作，抽換邏輯
 */
public interface DrawerHost {

    DrawerLayout getDrawer();
}
