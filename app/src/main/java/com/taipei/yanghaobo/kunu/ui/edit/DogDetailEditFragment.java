package com.taipei.yanghaobo.kunu.ui.edit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.taipei.yanghaobo.kunu.InjectorUtils;
import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;

import androidx.navigation.Navigation;

public class DogDetailEditFragment extends Fragment {

    private DogEntry mDogEntry;
    private EditText mTvDogNameEN;
    private EditText mTvDogNameCN;
    private EditText mTvDogNickname;
    private EditText mTvDogType;
    private EditText mTvDogInfo;
    private EditText mTvDogOrigin;
    private Button mBtUpdate;

    @NonNull
    public static DogDetailEditFragment newInstance() {
        return new DogDetailEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dog_detail_edit_fragment, container, false);

        mTvDogNameEN = view.findViewById(R.id.tv_dog_name_en);
        mTvDogNameCN = view.findViewById(R.id.tv_dog_name_cn);
        mTvDogNickname = view.findViewById(R.id.tv_dog_nicknames);
        mTvDogType = view.findViewById(R.id.tv_dog_type);
        mTvDogInfo = view.findViewById(R.id.tv_dog_info);
        mTvDogOrigin = view.findViewById(R.id.tv_dog_origin);
        mBtUpdate = view.findViewById(R.id.bt_update);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DogDetailEditFragmentArgs dogDetailEditFragmentArgs = DogDetailEditFragmentArgs.fromBundle(getArguments());
        int argsId = dogDetailEditFragmentArgs.getId();

        DogDetailEditVMFactory factory = InjectorUtils.provideDogDetailEditVMFactory(getContext(), argsId);
        DogDetailEditViewModel dogDetailEditViewModel = ViewModelProviders
                .of(this, factory)
                .get(DogDetailEditViewModel.class);
        dogDetailEditViewModel.getDogEntry().observe(this, dogEntry -> {
            if (dogEntry != null){
                mDogEntry = dogEntry;
                setDogDetail();
            }
        });

        mBtUpdate.setOnClickListener( bt -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }


            updateViewObject();
            dogDetailEditViewModel.updateDogDetail(mDogEntry);
            Navigation.findNavController(getView()).navigateUp();
        });
    }

    /**
     * 將編輯好的文字，存入Entity
     */
    private void updateViewObject(){
        String name_cn = mTvDogNameCN.getText().toString();
        String name_en = mTvDogNameEN.getText().toString();
        String nicknames = mTvDogNickname.getText().toString();
        String type = mTvDogType.getText().toString();
        String info = mTvDogInfo.getText().toString();
        String origin = mTvDogOrigin.getText().toString();
        mDogEntry.setName_cn(name_cn);
        mDogEntry.setName_en(name_en);
        mDogEntry.setNicknames(nicknames);
        mDogEntry.setType(type);
        mDogEntry.setInfo(info);
        mDogEntry.setOrigin(origin);
    }

    /**
     * 將 Entity 文字放入 畫面
     */
    private void setDogDetail() {
        String name_cn = mDogEntry.getName_cn();
        String name_en = mDogEntry.getName_en();
        String nicknames = mDogEntry.getNicknames();
        String type = mDogEntry.getType();
        String info = mDogEntry.getInfo();
        String origin = mDogEntry.getOrigin();
        mTvDogNameEN.setText(name_en);
        mTvDogNameCN.setText(name_cn);
        mTvDogNickname.setText(nicknames);
        mTvDogType.setText(type);
        mTvDogInfo.setText(info);
        mTvDogOrigin.setText(origin);
    }
}
