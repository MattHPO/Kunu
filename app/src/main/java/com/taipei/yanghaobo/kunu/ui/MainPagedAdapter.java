package com.taipei.yanghaobo.kunu.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
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

    protected MainPagedAdapter(Context context) {
        super(sDogEntryItemCallback);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MainPagedAdapter.DogCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.dog_card_item, viewGroup, false);
        return new DogCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPagedAdapter.DogCardViewHolder dogCardViewHolder, int i) {
        DogEntry dogEntry = getItem(i);
        dogCardViewHolder.mTextView.setText(dogEntry.getName_cn());
        dogCardViewHolder.mImageView.setImageResource(R.drawable.snow_full);
    }

    class DogCardViewHolder extends ViewHolder{

        final CardView mCardView;
        final ImageView mImageView;
        final TextView mTextView;

        public DogCardViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.dog_card);
            mImageView = itemView.findViewById(R.id.dog_card_photo);
            mTextView = itemView.findViewById(R.id.dog_card_text);
        }
    }

    private static DiffUtil.ItemCallback<DogEntry> sDogEntryItemCallback = new DiffUtil.ItemCallback<DogEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull DogEntry dogEntry, @NonNull DogEntry t1) {
            return dogEntry.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DogEntry dogEntry, @NonNull DogEntry t1) {
            return dogEntry.getName_cn().equals(t1.getName_cn());
        }
    };
}
