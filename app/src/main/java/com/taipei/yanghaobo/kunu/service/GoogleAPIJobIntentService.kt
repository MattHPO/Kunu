package com.taipei.yanghaobo.kunu.service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.taipei.yanghaobo.kunu.InjectorUtils

/**
 * 用於背景執行，介接Google APIs
 */
class GoogleAPIJobIntentService : JobIntentService() {

  override fun onHandleWork(intent: Intent) {
    // We have received work to do.  The system or framework is already
    // holding a wake lock for us at this point, so we can just go.
    val action = intent.action
    if (ACTION_GOOGLE_API_BOOK == action) {
      val googleNetWorkDataSoruce =
        InjectorUtils.provideGoogleNetWorkDataSoruce(this.applicationContext)
      val query = intent.getStringExtra(PARM_BOOK_QUERY)
      googleNetWorkDataSoruce.fetchBook(query)
    }
  }

  companion object {
    /**
     * Action !
     */
    private val ACTION_GOOGLE_API_BOOK = "action_google_api_book"
    /**
     * Job ID
     */
    private val JOB_ID = 1002

    /**
     * 查詢 book 的 query
     */
    private val PARM_BOOK_QUERY = "parm_book_query"

    fun getJobIntent(bookQuery: String): Intent {
      val intent = Intent(ACTION_GOOGLE_API_BOOK)
      intent.putExtra(PARM_BOOK_QUERY, bookQuery)
      return intent
    }

    /**
     * 啟動
     *
     * @param context
     * 就是Context
     * @param intent
     * 不同於 intentService's intent，
     */
    fun enqueueWork(context: Context, intent: Intent) {
      JobIntentService.enqueueWork(context, GoogleAPIJobIntentService::class.java, JOB_ID, intent)
    }
  }
}
