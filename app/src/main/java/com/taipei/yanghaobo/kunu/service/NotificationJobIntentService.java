package com.taipei.yanghaobo.kunu.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.ui.SettingActivity;

import java.util.Random;

/**
 *  取代 IntentService，對於 O 開始版本會以 Job 形式處理工作
 */
public class NotificationJobIntentService extends JobIntentService {
    /**
     * Action !
     */
    private static final String ACTION_DAILY_NT = "action_daily_nt";
    /**
     * JOB ID !
     */
    private static final int JOB_ID = 1001;

    // Notification 專區
    private static final String CHANNEL_ID_DAILY = "channel_id_daily";
    private static final String CHANNEL_NAME_DAILY = "每日狗狗提醒";
    private static final int PENDING_REQUEST_DAILY = 1;
    //

    @Nullable
    private NotificationManager mNotificationManager;

    /**
     * 取得執行 NotificationJobIntentService 的 Intent
     * @return
     *          用於 NotificationJobIntentService 的 Intent instance
     */
    public static Intent getJobIntent(){
        return new Intent(ACTION_DAILY_NT);
    }
    /**
     * Since Android O 開始，會作為 Job 被遞送給 JobSchedule 處理
     *
     * @param context
     *            就是Context
     * @param intent
     *            不同於 intentService's intent，
     */
    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent){
        enqueueWork(context, NotificationJobIntentService.class, JOB_ID, intent);
    }

    /**
     * 工作執行的地方
     * @param intent
     *            工作
     */
    @WorkerThread
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        final String action = intent.getAction();
        if (ACTION_DAILY_NT.equals(action)) {
            handleActionDailyNt();
            sendActionDailyNt();
        }
    }

    // private region ==============================================================================

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDailyNt() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID_DAILY, CHANNEL_NAME_DAILY, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void sendActionDailyNt(){
        int nextInt = new Random().nextInt(9999);

        Intent newIntent = SettingActivity.newIntent(this.getApplicationContext());
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.getApplicationContext());
        taskStackBuilder.addParentStack(SettingActivity.class);
        taskStackBuilder.addNextIntent(newIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(PENDING_REQUEST_DAILY
                , PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                PENDING_REQUEST_DAILY,
//                newIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_DAILY)
                    .setContentText("可愛的狗狗們療癒你喔")
                    .setContentTitle("狗奴！拜託來看看我～")
                    .setSmallIcon(R.drawable.kunu_bone_black)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(pendingIntent);

        mNotificationManager.notify(nextInt, builder.build());
    }

    @Deprecated
    public static void startActionDailyNt(@NonNull Context context) {
        Intent intent = new Intent(context, NotificationJobIntentService.class);
        intent.setAction(ACTION_DAILY_NT);
        context.startService(intent);
    }
}
