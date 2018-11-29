package com.taipei.yanghaobo.kunu.ui.API;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.Book;

import java.util.List;

public class BookListedAdapter extends RecyclerView.Adapter<BookListedAdapter.SearchViewHolder> {

    private final LayoutInflater mInflater;

    private List<Book> mBooksResult;

    public BookListedAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.kunu_book_item, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        Book book = mBooksResult.get(i);
        searchViewHolder.mBookAuthor.setText(book.getBookAuthor());
        searchViewHolder.mBookName.setText(book.getBookName());
    }

    @Override
    public int getItemCount() {
        return mBooksResult == null ? 0 : mBooksResult.size();
    }

    /**
     * 傳入 及 更新資料
     * @param newResult
     *             book 查詢結果
     */
    void swapResult(List<Book> newResult){

        if (mBooksResult == null){
            mBooksResult = newResult;
            notifyDataSetChanged();
        }
        else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mBooksResult.size();
                }

                @Override
                public int getNewListSize() {
                    return newResult.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mBooksResult.get(oldItemPosition).getBookName()
                            .equals(newResult.get(newItemPosition).getBookName());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    return mBooksResult.get(oldItemPosition).getBookAuthor()
                            .equals(newResult.get(newItemPosition).getBookAuthor());
                }
            });
            // 更新
            mBooksResult = newResult;
            // 將變更部分寄送給adapter
            diffResult.dispatchUpdatesTo(this);
        }
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView mBookName;
        TextView mBookAuthor;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            mBookName = itemView.findViewById(R.id.tv_book_name);
            mBookAuthor = itemView.findViewById(R.id.tv_book_author);
        }
    }
}
