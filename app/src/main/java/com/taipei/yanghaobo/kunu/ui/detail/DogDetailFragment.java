package com.taipei.yanghaobo.kunu.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;

import java.util.Objects;

import androidx.navigation.Navigation;

/**
 */
public class DogDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private DogEntry mDogEntry;
    private TextView mTvDogNameEN;
    private TextView mTvDogNameCN;
    private TextView mTvDogNickname;
    private TextView mTvDogType;
    private TextView mTvDogInfo;
    private TextView mTvDogOrigin;
    private FloatingActionButton mFabEditDog;

    public DogDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dogEntry Parameter 1.
     * @return A new instance of fragment DogDetailFragment.
     */
    @NonNull
    public static DogDetailFragment newInstance(DogEntry dogEntry) {
        DogDetailFragment fragment = new DogDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, dogEntry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kunu_dog_detail_fragment, container, false);
        //
//        if (getArguments() != null) {
//            mDogEntry = getArguments().getParcelable(ARG_PARAM1);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.findViewById(R.id.kunu_dog_detail_csl)
                    .setBackground(getResources().getDrawable(R.drawable.kunu_dog_detail_background_shape, null));
        } else {
            view.findViewById(R.id.kunu_dog_detail_csl)
                    .setBackground(getResources().getDrawable(R.drawable.kunu_dog_detail_background_shape));
        }

        mTvDogNameEN = view.findViewById(R.id.tv_dog_name_en);
        mTvDogNameCN = view.findViewById(R.id.tv_dog_name_cn);
        mTvDogNickname = view.findViewById(R.id.tv_dog_nicknames);
        mTvDogType = view.findViewById(R.id.tv_dog_type);
        mTvDogInfo = view.findViewById(R.id.tv_dog_info);
        mTvDogOrigin = view.findViewById(R.id.tv_dog_origin);
        mFabEditDog = view.findViewById(R.id.fab_edit_dog);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigation Component safeArgs
        DogDetailFragmentArgs safeArgs = DogDetailFragmentArgs.fromBundle(getArguments());
        int argsId = safeArgs.getId();
        //        FIXME: for test
        Toast.makeText(getContext(), "SafeArg ID is : " + argsId, Toast.LENGTH_SHORT).show();
        //
        DogDetailVMFactory dogDetailVMFactory = InjectorUtils.provideDogDetailVMFactory(getContext(), argsId);
        DogDetailViewModel detailViewModel = ViewModelProviders
                .of(this, dogDetailVMFactory)
                .get(DogDetailViewModel.class);
        detailViewModel.getOneDog().observe(this, dogEntry -> {
            mDogEntry = dogEntry;
            if (mDogEntry != null) {
                Toast.makeText(getContext(), "選到的狗狗是～ " + mDogEntry.getName_cn() + "!", Toast.LENGTH_SHORT).show();
                setDogDetail();
            }
        });

        mFabEditDog.setOnClickListener(fab -> {
            DogDetailFragmentDirections.ActionDogDetailFragmentToDogDetailEditFragment action = DogDetailFragmentDirections.actionDogDetailFragmentToDogDetailEditFragment();
            action.setId(argsId);
            Navigation.findNavController(Objects.requireNonNull(getView())).navigate(action);
        });
    }

    private void setDogDetail() {
        String name_cn = mDogEntry.getName_cn();
        StringBuilder name_en = new StringBuilder(mDogEntry.getName_en());
        name_en.insert(0, getString(R.string.kunu_label_name_en) + " ");
        StringBuilder nicknames = new StringBuilder(mDogEntry.getNicknames());
        nicknames.insert(0, getString(R.string.kunu_label_nicknames) + " ");
        StringBuilder type = new StringBuilder(mDogEntry.getType());
        type.insert(0, getString(R.string.kunu_label_type) + " ");
        StringBuilder info = new StringBuilder(mDogEntry.getInfo());
        if (TextUtils.isEmpty(info.toString())){
            info.insert(0, getString(R.string.dog_info_null));
        }
        info.insert(0, getString(R.string.kunu_label_info) + " ");
        StringBuilder origin = new StringBuilder(mDogEntry.getOrigin());
        origin.insert(0, getString(R.string.kunu_label_origin) + " ");

        mTvDogNameEN.setText(name_en);
        mTvDogNameCN.setText(name_cn);
        mTvDogNickname.setText(nicknames.toString());
        mTvDogType.setText(type.toString());
        mTvDogInfo.setText(info.toString());
        mTvDogOrigin.setText(origin.toString());
    }
}
