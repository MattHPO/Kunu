package com.taipei.yanghaobo.kunu.service;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class NotificationIntentService extends IntentService {
    private static final String ACTION_DAILY_NT = "action_daily_nt";
    private static final String CHANNEL_ID_DAILY = "channel_id_daily";
    private static final String CHANNEL_NAME_DAILY = "每日狗狗提醒";

    private NotificationManager mNotificationManager;

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionDailyNt(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DAILY_NT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            final String action = intent.getAction();
            if (ACTION_DAILY_NT.equals(action)) {
                handleActionDailyNt();
                sendActionDailyNt();
            }
        }
    }

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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_DAILY)
                .setContentText("可愛的狗狗們療癒你喔")
                .setContentTitle("狗奴！拜託來看看我～")
                .setSmallIcon(android.R.drawable.sym_action_email)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_MAX);
        mNotificationManager.notify(nextInt, builder.build());
    }

}
