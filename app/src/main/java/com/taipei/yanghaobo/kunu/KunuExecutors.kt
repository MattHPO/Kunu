package com.taipei.yanghaobo.kunu

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 執行緒池 ThreadPools
 */
class KunuExecutors
//    private static int sProcessors = 3;

private constructor(
  /**
   * @return Executor
   * DB ThreadPool
   */
  val diskIO: Executor,
  /**
   * @return Executor
   * 網路 ThreadPool
   */
  val networkIO: Executor,
  /**
   * @return Executor
   * 主 UI ThreadPool
   */
  val mainThread: Executor
) {

  /**
   * 取得主執行緒的接口 - Handler
   */
  private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
      mainThreadHandler.post(command)
    }
  }

  companion object {

    private val LOCK = Any()
    private var sInstance: KunuExecutors? = null

    val instance: KunuExecutors
      get() {
        if (sInstance == null) {
          synchronized(LOCK) {
            if (sInstance == null) {

              val processors = Runtime.getRuntime().availableProcessors()

              sInstance = KunuExecutors(
                Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(processors),
                MainThreadExecutor()
              )
            }
          }
        }
        return sInstance
      }
  }
}
