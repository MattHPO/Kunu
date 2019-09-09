package com.taipei.yanghaobo.kunu.ui

import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.service.NotificationJobService

import java.util.concurrent.TimeUnit

/**
 * A [PreferenceFragmentCompat] subclass.
 */
class SettingFragment : PreferenceFragmentCompat(),
  SharedPreferences.OnSharedPreferenceChangeListener {

  private// 必定要有，會影響重複週期的間隔時間
  val jobBuilder: JobInfo.Builder
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() {
      val builder = JobInfo.Builder(
        JOB_NOTIFICATION,
        ComponentName(activity!!.packageName, NotificationJobService::class.java.name)
      )
        .setPersisted(true)
        .setRequiresCharging(false)
        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        builder.setMinimumLatency(TimeUnit.SECONDS.toMillis(5))
          .setOverrideDeadline(TimeUnit.SECONDS.toMillis(5))
          .setBackoffCriteria(TimeUnit.SECONDS.toMillis(1), JobInfo.BACKOFF_POLICY_LINEAR)
      } else {
        builder.setPeriodic(3000)
      }
      return builder
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    setHasOptionsMenu(true)
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onCreatePreferences(bundle: Bundle, rootKey: String) {
    setPreferencesFromResource(R.xml.preferences, rootKey)
    PreferenceManager.setDefaultValues(activity!!, R.xml.preferences, false)
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    when (key) {
      KEY_PREF_NT -> {

        val isNtSwitchOn = sharedPreferences.getBoolean(KEY_PREF_NT, false)
        val jobScheduler = activity!!
          .applicationContext
          .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        if (!isNtSwitchOn) {
          jobScheduler.cancel(JOB_NOTIFICATION)
        } else {
          val jobBuilder = jobBuilder
          jobScheduler.schedule(jobBuilder.build())
        }
      }
    }
  }

  override fun onPreferenceTreeClick(preference: Preference): Boolean {
    //        String preferenceKey = preference.getKey();
    //        switch (preferenceKey){
    //            case KEY_PREF_NT:
    //                Toast.makeText(getActivity(), "狗狗通知開啟 !", Toast.LENGTH_SHORT).show();
    //        }
    return false
  }

  override fun onResume() {
    super.onResume()
    preferenceScreen.sharedPreferences
      .registerOnSharedPreferenceChangeListener(this)
  }

  override fun onPause() {
    super.onPause()
    preferenceScreen.sharedPreferences
      .unregisterOnSharedPreferenceChangeListener(this)
  }

  companion object {

    private val KEY_PREF_NT = "pref_notificationSwitch"

    private val JOB_NOTIFICATION = 6
  }
}
