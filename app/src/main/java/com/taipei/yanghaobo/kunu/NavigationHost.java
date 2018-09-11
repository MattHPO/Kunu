package com.taipei.yanghaobo.kunu;

import android.support.v4.app.Fragment;

/**
 *  由 Activity 統一實作，抽換邏輯
 */
public interface NavigationHost {

    void navigateTo(Fragment fragment, boolean addToBackstack);
}
