package com.taipei.yanghaobo.kunu.network;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

final class NetＷorkUtils {

    private static final String TAG = NetＷorkUtils.class.getSimpleName();

    //    URL =========================================
    private static final String GOOGLE_BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";

    //    PARAMETERS ==================================
    private static final String QUERY_PARAM = "q";

    private static final String MAX_RESULT_PARAM = "maxResults";

    private static final String PRINT_TYPE_PARAM = "printType";

    /**
     * 最大查詢數量
     */
    private static final String MAX_RESULT_VARIABLE = "10";
//  ===============================================
    /**
     * @param queryString
     *          查詢書本的條件
     * @return bookJSONString
     *          回傳結果
     */
    public static String getGoogleBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String bookJSONString = null;

        try {
            Uri queryURI = Uri.parse(GOOGLE_BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULT_PARAM, "10")
                    .appendQueryParameter(PRINT_TYPE_PARAM, "books")
                    .build();
            URL requestURL = new URL(queryURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
//            沒有收到任何回應
            if (stringBuilder.length() == 0){
                return null;
            }
//            實際存檔地方
            bookJSONString = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            //
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(TAG, "getGoogleBookInfo: " + bookJSONString );
        return bookJSONString;
    }
}
