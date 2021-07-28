package com.trivia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trivia.R;
import com.trivia.databinding.LayoutHistoryRowBinding;
import com.trivia.model.History;
import com.trivia.model.History;

import java.util.List;

public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<History> mArrayList;
    public int row_index = 0;
    private OnItemClickListener onItemClickListener;

    public HistoryAdapter(Context mContext,
                          List<History> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    public interface OnItemClickListener {
        public void onItemClick( int position);
    }

    public void setOnItemClickListener(OnItemClickListener myClickListener) {
        this.onItemClickListener = myClickListener;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutHistoryRowBinding itemCardBinding = LayoutHistoryRowBinding.inflate(layoutInflater, parent, false);
        return new HorizontalViewHolder(itemCardBinding);

    }

    @Override
    public void onBindViewHolder(final HorizontalViewHolder holder, final int position) {
        final History options = mArrayList.get(position);
        holder.binding.txtName.setText(options.getName());
        holder.binding.txtDate.setText(options.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
onItemClickListener.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        public LayoutHistoryRowBinding binding;

        public HorizontalViewHolder(LayoutHistoryRowBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}


