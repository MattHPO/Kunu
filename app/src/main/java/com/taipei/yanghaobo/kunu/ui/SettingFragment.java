package com.taipei.yanghaobo.kunu.ui;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.service.NotificationJobService;

import java.util.concurrent.TimeUnit;

/**
 * A {@link PreferenceFragmentCompat} subclass.
 */
public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_PREF_NT = "pref_notificationSwitch";

    private static final int JOB_NOTIFICATION = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSharedPreferenceChanged(@NonNull SharedPreferences sharedPreferences, @NonNull String key) {
        switch (key) {
            case KEY_PREF_NT:

                boolean isNtSwitchOn = sharedPreferences.getBoolean(KEY_PREF_NT, false);
                JobScheduler jobScheduler =
                        (JobScheduler) getActivity()
                                .getApplicationContext()
                                .getSystemService(Context.JOB_SCHEDULER_SERVICE);
                if (!isNtSwitchOn) {
                    jobScheduler.cancel(JOB_NOTIFICATION);
                } else {
                    JobInfo.Builder jobBuilder = getJobBuilder();
                    jobScheduler.schedule(jobBuilder.build());
                }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private JobInfo.Builder getJobBuilder() {
        JobInfo.Builder builder = new JobInfo.Builder(
                JOB_NOTIFICATION,
                new ComponentName(getActivity().getPackageName(), NotificationJobService.class.getName()))
                .setPersisted(true)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(TimeUnit.SECONDS.toMillis(5))
                    .setOverrideDeadline(TimeUnit.SECONDS.toMillis(5))
                    // 必定要有，會影響重複週期的間隔時間
                    .setBackoffCriteria(TimeUnit.SECONDS.toMillis(1), JobInfo.BACKOFF_POLICY_LINEAR);
        }
        else {
            builder.setPeriodic(3000);
        }
        return builder;
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
//        String preferenceKey = preference.getKey();
//        switch (preferenceKey){
//            case KEY_PREF_NT:
//                Toast.makeText(getActivity(), "狗狗通知開啟 !", Toast.LENGTH_SHORT).show();
//        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
