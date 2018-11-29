package com.taipei.yanghaobo.kunu.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;

import com.taipei.yanghaobo.kunu.KunuExecutors;
import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.Book;
import com.taipei.yanghaobo.kunu.service.GoogleAPIJobIntentService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 取 https://developers.google.com/apis-explorer/#p/ 中
 * Google APIs Explorer 網頁
 */
public class GoogleNetWorkDataSoruce {

    private static final String TAG = GoogleNetWorkDataSoruce.class.getSimpleName();
    private static final Object LOCK = new Object();

    private static GoogleNetWorkDataSoruce sInstance;
    /**
     *  ApplicationContext
     */
    private final Context mContext;

    private final KunuExecutors mKunuExecutors;

    private final MutableLiveData<List<Book>> mBookList;

    public GoogleNetWorkDataSoruce(Context context, KunuExecutors kunuExecutors) {
        mContext = context;
        mKunuExecutors = kunuExecutors;
        mBookList = new MutableLiveData<>();
    }

    public static GoogleNetWorkDataSoruce getInstance(Context context, KunuExecutors kunuExecutors) {
        if (sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance = new GoogleNetWorkDataSoruce(context, kunuExecutors);
                }
            }
        }
        return sInstance;
    }

    /**
     * 取得 書本的 liveData
     *
     * @return
     *        Google 書籍資料
     */
    public MutableLiveData<List<Book>> getBookList(String query) {
        startFetchGoogleBookService(query);
        return mBookList;
    }

    /**
     * 啟動 IntentService 進行背景工作
     * @param query
     *           查詢 book 的 query
     */
    private void startFetchGoogleBookService(String query){
        Intent intent = GoogleAPIJobIntentService.getJobIntent(query);
        GoogleAPIJobIntentService.enqueueWork(mContext.getApplicationContext(), intent);
    }

    /**
     * 取得 book 的邏輯
     */
    public void fetchBook(String query){
        mKunuExecutors.getNetworkIO().execute(() -> {

            String googleBookInfo = NetＷorkUtils.getGoogleBookInfo(query);
            ArrayList<Book> bookList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(googleBookInfo);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                String title = null;
                String authors = null;

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject book = jsonArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                    Book eachBook = null;

                    if (!volumeInfo.isNull("title") && !volumeInfo.isNull("authors")) {
                        title = volumeInfo.getString("title");
                        authors = volumeInfo.getString("authors");

                        if (title != null && authors != null){
//                            FIXME
                            authors = authors.substring(2, authors.length() - 2);

                            eachBook = new Book(title, authors);
                        }
                        else {
                            eachBook = new Book(" :( ", mContext.getString(R.string.fetch_data_fail));
                        }
                        bookList.add(eachBook);
                    }
                }

                if (bookList.size() > 0){
                    mBookList.postValue(bookList);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
