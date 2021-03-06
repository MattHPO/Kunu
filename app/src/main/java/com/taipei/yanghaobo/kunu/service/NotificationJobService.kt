package com.taipei.yanghaobo.kunu.service

import android.annotation.TargetApi
import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class NotificationJobService : JobService() {
  override fun onStartJob(params: JobParameters): Boolean {
    // 啟動 JobIntentService
    val jobIntent = NotificationJobIntentService.jobIntent
    NotificationJobIntentService.enqueueWork(applicationContext, jobIntent)
    //        N 需要此才能進行 週期排程
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
      // 需要 true
      // 回傳 true 交給 jobFinished
      jobFinished(params, true)
      return true
    }
    return false
  }

  override fun onStopJob(params: JobParameters): Boolean {
    return false
  }

  override fun onDestroy() {
    super.onDestroy()
    //        Oreo 開始 只有靠此方法才能進行 15分鐘內的 週期排程
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    //            JobScheduler jobScheduler =
    //                    (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
    //            JobInfo.Builder builder = new JobInfo.Builder(6,
    //                    new ComponentName(getPackageName(), NotificationJobService.class.getName()))
    //                    .setPersisted(true)
    //                    .setMinimumLatency(TimeUnit.SECONDS.toMillis(5))
    //                    .setOverrideDeadline(TimeUnit.SECONDS.toMillis(5))
    //                    // 必定要有，會影響重複週期的間隔時間
    //                    .setBackoffCriteria(TimeUnit.SECONDS.toMillis(1), JobInfo.BACKOFF_POLICY_LINEAR)
    //                    .setRequiresCharging(false)
    //                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);;
    //            jobScheduler.schedule(builder.build());
    //        }
  }
}
