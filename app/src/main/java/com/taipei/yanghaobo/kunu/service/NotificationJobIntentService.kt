package com.taipei.yanghaobo.kunu.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.WorkerThread
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder

import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.ui.SettingActivity

import java.util.Random

/**
 * 取代 IntentService，對於 O 開始版本會以 Job 形式處理工作
 */
class NotificationJobIntentService : JobIntentService() {
  //

  private var mNotificationManager: NotificationManager? = null

  /**
   * 工作執行的地方
   * @param intent
   * 工作
   */
  @WorkerThread
  override fun onHandleWork(intent: Intent) {
    // We have received work to do.  The system or framework is already
    // holding a wake lock for us at this point, so we can just go.
    val action = intent.action
    if (ACTION_DAILY_NT == action) {
      handleActionDailyNt()
      sendActionDailyNt()
    }
  }

  // private region ==============================================================================

  /**
   * Handle action Foo in the provided background thread with the provided
   * parameters.
   */
  private fun handleActionDailyNt() {
    mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      val notificationChannel = NotificationChannel(
        CHANNEL_ID_DAILY, CHANNEL_NAME_DAILY, NotificationManager.IMPORTANCE_HIGH
      )
      mNotificationManager!!.createNotificationChannel(notificationChannel)
    }
  }

  private fun sendActionDailyNt() {
    val nextInt = Random().nextInt(9999)

    val newIntent = SettingActivity.newIntent(this.applicationContext)
    val taskStackBuilder = TaskStackBuilder.create(this.applicationContext)
    taskStackBuilder.addParentStack(SettingActivity::class.java)
    taskStackBuilder.addNextIntent(newIntent)
    val pendingIntent =
      taskStackBuilder.getPendingIntent(PENDING_REQUEST_DAILY, PendingIntent.FLAG_UPDATE_CURRENT)
    //        PendingIntent pendingIntent = PendingIntent.getActivity(this,
    //                PENDING_REQUEST_DAILY,
    //                newIntent,
    //                PendingIntent.FLAG_UPDATE_CURRENT);

    val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID_DAILY)
      .setContentText("可愛的狗狗們療癒你喔")
      .setContentTitle("狗奴！拜託來看看我～")
      .setSmallIcon(R.drawable.kunu_bone_black)
      .setWhen(System.currentTimeMillis())
      .setPriority(NotificationCompat.PRIORITY_MAX)
      .setContentIntent(pendingIntent)

    mNotificationManager!!.notify(nextInt, builder.build())
  }

  companion object {
    /**
     * Action !
     */
    private val ACTION_DAILY_NT = "action_daily_nt"
    /**
     * JOB ID !
     */
    private val JOB_ID = 1001

    // Notification 專區
    private val CHANNEL_ID_DAILY = "channel_id_daily"
    private val CHANNEL_NAME_DAILY = "每日狗狗提醒"
    private val PENDING_REQUEST_DAILY = 1

    /**
     * 取得執行 NotificationJobIntentService 的 Intent
     * @return
     * 用於 NotificationJobIntentService 的 Intent instance
     */
    val jobIntent: Intent
      get() = Intent(ACTION_DAILY_NT)

    /**
     * Since Android O 開始，會作為 Job 被遞送給 JobSchedule 處理
     *
     * @param context
     * 就是Context
     * @param intent
     * 不同於 intentService's intent，
     */
    fun enqueueWork(context: Context, intent: Intent) {
      JobIntentService.enqueueWork(
        context,
        NotificationJobIntentService::class.java,
        JOB_ID,
        intent
      )
    }

    @Deprecated("")
    fun startActionDailyNt(context: Context) {
      val intent = Intent(context, NotificationJobIntentService::class.java)
      intent.action = ACTION_DAILY_NT
      context.startService(intent)
    }
  }
}
