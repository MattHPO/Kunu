package com.taipei.yanghaobo.kunu.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class MainPagedAdapter extends PagedListAdapter<DogEntry, MainPagedAdapter.DogCardViewHolder> {

    private final LayoutInflater mInflater;

    public MainPagedAdapter(Context context) {
        super(sDogEntryItemCallback);
        mInflater = LayoutInflater.from(context);
    }

    class DogCardViewHolder extends ViewHolder{
//        final CardView mCardView;
        private final ImageView mImageView;
        private final TextView mTextView;

        public DogCardViewHolder(@NonNull View itemView) {
            super(itemView);
//            mCardView = itemView.findViewById(R.id.dog_card);
            this.mImageView = itemView.findViewById(R.id.dog_card_photo);
            this.mTextView = itemView.findViewById(R.id.dog_card_text);
        }
    }

    @NonNull
    @Override
    public DogCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.dog_card_item, viewGroup, false);
        return new DogCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DogCardViewHolder dogCardViewHolder, int i) {
        DogEntry dogEntry = getItem(i);
        if (dogEntry != null) {
            dogCardViewHolder.mTextView.setText(dogEntry.getName_cn());
            dogCardViewHolder.mImageView.setImageResource(R.drawable.snow_full);
        }
        else {
            dogCardViewHolder.mTextView.setText("沒有資料");
            dogCardViewHolder.mImageView.setImageResource(R.drawable.snow_shake);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    //
    private static DiffUtil.ItemCallback<DogEntry> sDogEntryItemCallback = new DiffUtil.ItemCallback<DogEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull /* old */ DogEntry dogEntry, @NonNull /* new */ DogEntry t1) {
            return dogEntry.getId() == t1.getId();
//            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull /* old */ DogEntry dogEntry, @NonNull /* new */ DogEntry t1) {
            return dogEntry.getName_cn().equals(t1.getName_cn());
//            return false;
        }
    };
}
