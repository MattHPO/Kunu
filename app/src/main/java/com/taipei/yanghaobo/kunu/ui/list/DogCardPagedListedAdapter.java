package com.taipei.yanghaobo.kunu.ui.list;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogListEntry;

public class DogCardPagedListedAdapter extends PagedListAdapter<DogListEntry, DogCardPagedListedAdapter.DogCardViewHolder> {

    private final LayoutInflater mInflater;

    private DogCardOnClickedListener mDogCardOnClickedListener;

    public interface DogCardOnClickedListener {
        void onDogItemClick(DogListEntry dogEntry);
    }

    public DogCardPagedListedAdapter(Context context) {
        super(sDogListDiffUtil);
//        設定 DiffUtil 進行 資料更新時的計算
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    private static DiffUtil.ItemCallback<DogListEntry> sDogListDiffUtil = new DiffUtil.ItemCallback<DogListEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull /* old */ DogListEntry dogListEntry, @NonNull /* new */ DogListEntry t1) {
            return dogListEntry.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull /* old */ DogListEntry dogListEntry, @NonNull /* new */ DogListEntry t1) {
            return dogListEntry.getName_cn().equals(t1.getName_cn());
        }
    };

    class DogCardViewHolder extends RecyclerView.ViewHolder {
        //        final CardView mCardView;
        private final ImageView mImageView;
        private final TextView mTextView;

        public DogCardViewHolder(@NonNull View itemView) {
            super(itemView);
//            mCardView = itemView.findViewById(R.id.dog_card);
            this.mImageView = itemView.findViewById(R.id.img_dog_photo);
            this.mTextView = itemView.findViewById(R.id.tv_dog_name);
        }
    }

    @NonNull
    @Override
    public DogCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.kunu_dog_card, viewGroup, false);

        itemView.findViewById(R.id.tv_dog_name).setOnClickListener(v -> {
            if (mDogCardOnClickedListener != null)
            mDogCardOnClickedListener.onDogItemClick((DogListEntry) itemView.getTag());
        });
        return new DogCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DogCardViewHolder dogCardViewHolder, int i) {
        DogListEntry dogListEntry = getItem(i);
        if (dogListEntry != null) {
            dogCardViewHolder.mTextView.setText(dogListEntry.getName_cn());
            dogCardViewHolder.mImageView.setImageResource(R.drawable.kunu_snow_shake);
            dogCardViewHolder.itemView.setTag(dogListEntry);
        }
        else {
            dogCardViewHolder.mTextView.setText("沒有資料");
            dogCardViewHolder.mImageView.setImageResource(R.drawable.kunu_snow_full);
        }
    }
    //

    //
    public void setDogCardOnClickedListener(DogCardOnClickedListener listener) {
        this.mDogCardOnClickedListener = listener;
    }
}
