package com.taipei.yanghaobo.kunu;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 執行緒池 ThreadPools
 */
public class KunuExecutors {

    private static final Object LOCK = new Object();
    private static KunuExecutors sInstance;

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
//    private static int sProcessors = 3;

    private KunuExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static KunuExecutors getInstance() {
        if (sInstance == null){
            synchronized (LOCK) {
                if (sInstance == null){

                    int processors = Runtime.getRuntime().availableProcessors();

                    sInstance = new KunuExecutors(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(processors),
                            new MainThreadExecutor()
                    );
                }
            }
        }
        return sInstance;
    }

    /**
     * @return Executor
     *          DB ThreadPool
     */
    public Executor getDiskIO() {
        return diskIO;
    }

    /**
     * @return Executor
     *          網路 ThreadPool
     */
    public Executor getNetworkIO() {
        return networkIO;
    }

    /**
     * @return Executor
     *          主 UI ThreadPool
     */
    public Executor getMainThread() {
        return mainThread;
    }

    /**
     * 取得主執行緒的接口 - Handler
     */
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
