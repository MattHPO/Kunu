package com.taipei.yanghaobo.kunu.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.taipei.yanghaobo.kunu.DrawerHost;
import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogListEntry;

import java.util.Objects;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

/**
 * 狗狗品種列表
 */
public class DogGridFragment extends Fragment implements DogCardPagedListedAdapter.DogCardOnClickedListener {

    private RecyclerView mKunuDogRecycler;

    public DogGridFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.kunu_dog_grid_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mKunuDogRecycler = view.findViewById(R.id.kunu_dog_recycler);
        mKunuDogRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DogCardPagedListedAdapter adapter = new DogCardPagedListedAdapter(getContext());
        adapter.setDogCardOnClickedListener(this);
        mKunuDogRecycler.setAdapter(adapter);

        DogCardVMFactory dogCardVMFactory = InjectorUtils.provideDogCardVMFactory(getContext());
        DogCardViewModel dogCardViewModel =
                ViewModelProviders
                        .of(getActivity(), dogCardVMFactory)
                        .get(DogCardViewModel.class);

        dogCardViewModel.getAllDogList().observe(this, dogEntries -> {
            Toast.makeText(getContext(), "DogLise size = " + Objects.requireNonNull(dogEntries).size(), Toast.LENGTH_SHORT).show();
            adapter.submitList(dogEntries);
        });

        setUpToolBar(view);
    }

    private void setUpToolBar(View view) {
        Toolbar kunuToolBar = view.findViewById(R.id.kunu_doglist_toolbar);
//        替代預設的 ActionBar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity instanceof DrawerHost) {
            activity.setSupportActionBar(kunuToolBar);
//            專門for CollapsingToolbarLayout 的 Method
            NavigationUI.setupWithNavController(
                    view.findViewById(R.id.kunu_doglist_collapsingToolbarLayout),
                    kunuToolBar,
                    Navigation.findNavController(view),
                    ((DrawerHost) getActivity()).getDrawer());
        } else if (activity != null) {
            activity.setSupportActionBar(kunuToolBar);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.kunu_toolbar_menu, menu);
    }

    //    Toolbar 的 點擊事件callback
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.kunu_actions_settings:

                Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.action_dogGridFragment_to_settingActivity);
                return true;

            case R.id.kunu_menu_layout_type:
                if (mKunuDogRecycler.getLayoutManager() instanceof GridLayoutManager) {
                    mKunuDogRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    item.setIcon(R.drawable.kunu_grid_list_icon);
                    mKunuDogRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            outRect.left = getResources().getDimensionPixelSize(R.dimen.basic_zero);
                            outRect.right = getResources().getDimensionPixelSize(R.dimen.basic_zero);
                        }
                    });
                }
                else {
                    mKunuDogRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
                    mKunuDogRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            outRect.left = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
                            outRect.right = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
                        }
                    });
                    item.setIcon(R.drawable.kunu_linear_list_icon);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDogItemClick(@NonNull DogListEntry dogListEntry) {

        DogGridFragmentDirections.ActionDogGridFragmentToDogDetailFragment actionToDogDetailFragment = DogGridFragmentDirections.actionDogGridFragmentToDogDetailFragment()
                .setId(dogListEntry.getId());
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(actionToDogDetailFragment);
    }
}
