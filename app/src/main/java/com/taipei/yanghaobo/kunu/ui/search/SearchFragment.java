package com.taipei.yanghaobo.kunu.ui.search;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
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
import com.taipei.yanghaobo.kunu.db.DogListEntry;
import com.taipei.yanghaobo.kunu.ui.list.DogCardPagedListedAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements DogCardPagedListedAdapter.DogCardOnClickedListener {

    private TextInputLayout mTextInputQuery;
    private TextInputEditText mEditInputTextQuery;
    private RecyclerView mQueryResultList;
    private MaterialButton mBtQuery;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_dog, container, false);

        mTextInputQuery = view.findViewById(R.id.text_input_query);
        mEditInputTextQuery = view.findViewById(R.id.edit_input_text_query);

        mQueryResultList = view.findViewById(R.id.rv_query_result_list);
        mQueryResultList.setHasFixedSize(true);

        mBtQuery = view.findViewById(R.id.bt_search);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mQueryResultList.setLayoutManager(new LinearLayoutManager(getContext()));
        DogCardPagedListedAdapter adapter = new DogCardPagedListedAdapter(getContext());
        adapter.setDogCardOnClickedListener(this);
        mQueryResultList.setAdapter(adapter);

        SearchVMFactory factory = InjectorUtils.provideSearchVMFactory(getContext());
        SearchViewModel searchViewModel = ViewModelProviders.of(this, factory).get(SearchViewModel.class);
        searchViewModel.getQueryResultList().observe(this, new Observer<PagedList<DogListEntry>>() {
            @Override
            public void onChanged(@Nullable PagedList<DogListEntry> dogListEntries) {
                adapter.submitList(dogListEntries);
            }
        });

        mBtQuery.setOnClickListener(bt -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            if (isQueryValid(mEditInputTextQuery.getText())){
                mTextInputQuery.setError(null);
                mTextInputQuery.setHint(getString(R.string.input_query_word));
                searchViewModel.queryDog(mEditInputTextQuery.getText().toString());
            }
            else {
                mTextInputQuery.setError(getString(R.string.query_error));
                mTextInputQuery.setHint(getString(R.string.input_something));
            }
        });
    }

    private boolean isQueryValid(Editable editable){

        if (TextUtils.isEmpty(editable)){
            return false;
        }
        return true;
    }

    @Override
    public void onDogItemClick(DogListEntry dogEntry) {

    }
}
