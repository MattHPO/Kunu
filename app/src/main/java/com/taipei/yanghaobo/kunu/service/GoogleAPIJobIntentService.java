package com.taipei.yanghaobo.kunu.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.network.GoogleNetWorkDataSoruce;

/**
 * 用於背景執行，介接Google APIs
 */
public class GoogleAPIJobIntentService extends JobIntentService{
    /**
     * Action !
     */
    private static final String ACTION_GOOGLE_API_BOOK = "action_google_api_book";
    /**
     * Job ID
     */
    private static final int JOB_ID = 1002;

    /**
     * 查詢 book 的 query
     */
    private static final String PARM_BOOK_QUERY = "parm_book_query";

    public static Intent getJobIntent(String bookQuery){
        Intent intent = new Intent(ACTION_GOOGLE_API_BOOK);
        intent.putExtra(PARM_BOOK_QUERY, bookQuery);
        return intent;
    }
    /**
     *  啟動
     *
     * @param context
     *            就是Context
     * @param intent
     *            不同於 intentService's intent，
     */
    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent){
        enqueueWork(context, GoogleAPIJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        final String action = intent.getAction();
        if (ACTION_GOOGLE_API_BOOK.equals(action)) {
            GoogleNetWorkDataSoruce googleNetWorkDataSoruce = InjectorUtils.provideGoogleNetWorkDataSoruce(this.getApplicationContext());
            String query = intent.getStringExtra(PARM_BOOK_QUERY);
            googleNetWorkDataSoruce.fetchBook(query);
        }
    }
}
