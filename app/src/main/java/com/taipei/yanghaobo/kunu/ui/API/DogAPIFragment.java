package com.taipei.yanghaobo.kunu.ui.API;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.R;


/**
 * 介接 Google APIs 用！
 */
public class DogAPIFragment extends Fragment {

    private RecyclerView mBookQueryResultRV;
    private TextInputLayout mTextInputSearch;
    private TextInputEditText mEditInputTextSearch;
    private MaterialButton mBtSearch;

    public DogAPIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_google_api, container, false);

        mBookQueryResultRV = view.findViewById(R.id.rv_book_query_result);
        mTextInputSearch = view.findViewById(R.id.text_input_search);
        mEditInputTextSearch = view.findViewById(R.id.edit_input_text_search);
        mBtSearch = view.findViewById(R.id.bt_search);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookListedAdapter adapter = new BookListedAdapter(getContext());
        mBookQueryResultRV.setAdapter(adapter);
        mBookQueryResultRV.setLayoutManager(new LinearLayoutManager(getContext()));
        BookListedVMFactory factory = InjectorUtils.provideBookListedVMFactory(getContext());
        BookListedViewModel bookListedViewModel = ViewModelProviders.of(this, factory)
                .get(BookListedViewModel.class);
        bookListedViewModel.getBookQueryResult().observe(this, books -> adapter.swapResult(books));

        mBtSearch.setOnClickListener(bt -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            // 檢察網路連線
            ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected()) {
                if (isQueryValid(mEditInputTextSearch.getText())){
                    mTextInputSearch.setError(null);
                    mTextInputSearch.setHint(getString(R.string.query_google_book));
                    // Network query
                    bookListedViewModel.queryBookListed(mEditInputTextSearch.getText().toString());
                }
                else {
                    mTextInputSearch.setError(getString(R.string.query_error));
                    mTextInputSearch.setHint(getString(R.string.input_something));
                }
            }
            else {
                mTextInputSearch.setError(getString(R.string.error_no_internet));
                mTextInputSearch.setHint(getString(R.string.error_hint_no_internet));
            }

        });
    }

    private boolean isQueryValid(Editable editable){
        if (TextUtils.isEmpty(editable)){
            return false;
        }
        return true;
    }
}
