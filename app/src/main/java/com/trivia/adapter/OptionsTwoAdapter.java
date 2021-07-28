package com.trivia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trivia.R;
import com.trivia.databinding.LayoutOptionRowBinding;
import com.trivia.model.Option;

import java.util.ArrayList;
import java.util.List;

public class OptionsTwoAdapter extends
        RecyclerView.Adapter<OptionsTwoAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<Option> mArrayList;
    public ArrayList<Option> selctedArray = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public OptionsTwoAdapter(Context mContext,
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

                if (selctedArray.size() > 0) {
                    if (selctedArray.contains(options)) {
                        holder.binding.txtOptions.setTextColor(mContext.getResources().getColor(R.color.black));
                        holder.binding.txtOptions.setBackgroundResource(R.color.white);
                        selctedArray.remove(options);
                    } else {
                        holder.binding.txtOptions.setTextColor(mContext.getResources().getColor(R.color.black));
                        holder.binding.txtOptions.setBackgroundResource(R.color.green);
                        selctedArray.add(options);
                    }
                } else {
                    holder.binding.txtOptions.setTextColor(mContext.getResources().getColor(R.color.black));
                    holder.binding.txtOptions.setBackgroundResource(R.color.green);
                    selctedArray.add(options);
                }
            }
        });
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


