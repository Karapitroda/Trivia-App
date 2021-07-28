package com.trivia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trivia.R;
import com.trivia.databinding.LayoutOptionRowBinding;
import com.trivia.model.Option;

import java.util.List;

public class OptionsOneAdapter extends
        RecyclerView.Adapter<OptionsOneAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<Option> mArrayList;
    public int row_index = -1;
    private OnItemClickListener onItemClickListener;
    public OptionsOneAdapter(Context mContext,
                             List<Option> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }
    public interface OnItemClickListener {
        public void onItemClick(int type, int position);
    }
    public void setOnItemClickListener(OnItemClickListener myClickListener) {
        this.onItemClickListener = myClickListener;
    }
    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutOptionRowBinding itemCardBinding = LayoutOptionRowBinding.inflate(layoutInflater, parent, false);
        return new HorizontalViewHolder(itemCardBinding);

    }

    @Override
    public void onBindViewHolder(final HorizontalViewHolder holder, final int position) {
        final Option options = mArrayList.get(position);
        holder.binding.txtOptions.setText(options.getOption());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;

                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.binding.txtOptions.setBackgroundResource(R.color.green);
            holder.binding.txtOptions.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.binding.txtOptions.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.binding.txtOptions.setBackgroundResource(R.color.white);
        }

    }


    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        public LayoutOptionRowBinding binding;

        public HorizontalViewHolder(LayoutOptionRowBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}


