package com.taipei.yanghaobo.kunu.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taipei.yanghaobo.kunu.R;

import java.util.Objects;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

/**
 */
public class FavoriteDogListFragment extends Fragment {

    private Toolbar mToolbar;

    public FavoriteDogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_dog_list, container, false);
        setUpToolbar(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavigationUI.setupWithNavController(mToolbar, Navigation.findNavController(view));
    }

    private void setUpToolbar(View view) {
        setHasOptionsMenu(true);
        mToolbar = view.findViewById(R.id.kunu_basic_toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.kunu_basic_menu, menu);
    }
}
