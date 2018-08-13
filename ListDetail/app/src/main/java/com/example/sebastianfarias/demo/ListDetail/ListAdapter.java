package com.example.sebastianfarias.demo.ListDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sebastianfarias.demo.ListDetail.Model.Item;
import com.example.sebastianfarias.demo.R;
import com.example.sebastianfarias.demo.Utils.ListRVClick;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public List<Item> items;
    public Context context;
    public ListRVClick listRVClickListener;

    public ListAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_cell_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTitle.setText(items.get(i).getTitle());
        viewHolder.mDescription.setText(items.get(i).getDescription());
        Glide.with(context)
                .load(items.get(i).getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.baseline_photo_black_48)
                        .error(R.drawable.baseline_photo_black_48)
                        .centerCrop())
                .into(viewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setListRVClickListener(ListRVClick listRVClick) {
        this.listRVClickListener = listRVClick;
    }

    public void addNewItems(List<Item> list) {
        this.items = new ArrayList<>();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public Item selectedItem(int position){
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle;
        public TextView mDescription;
        public ImageView mImage;
        public ViewHolder(View view) {
            super(view);
            mTitle          = (TextView) itemView.findViewById(R.id.list_cell_title);
            mDescription    = (TextView) itemView.findViewById(R.id.list_cell_detail);
            mImage          = (ImageView) itemView.findViewById(R.id.list_cell_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listRVClickListener != null) listRVClickListener.onClick(view, getPosition());
        }
    }
}
