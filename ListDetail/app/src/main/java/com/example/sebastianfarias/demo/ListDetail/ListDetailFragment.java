package com.example.sebastianfarias.demo.ListDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sebastianfarias.demo.ListDetail.Model.Item;
import com.example.sebastianfarias.demo.R;
import com.example.sebastianfarias.demo.Utils.Constants;

public class ListDetailFragment extends Fragment {
    private TextView mTitle;
    private TextView mDescription;
    private ImageView mImage;
    private Item item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_detail_view, container, false);
        getActivity().setTitle(getContext().getString(R.string.title_list_detail));
        item = (Item) this.getArguments().get(Constants.KEY_ITEM);
        mTitle          = (TextView) view.findViewById(R.id.list_detail_title);
        mDescription    = (TextView) view.findViewById(R.id.list_detail_detail);
        mImage          = (ImageView) view.findViewById(R.id.list_detail_image);

        mTitle.setText(item.getTitle());
        mDescription.setText(item.getDescription());
        Glide.with(this)
                .load(item.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.baseline_photo_black_48)
                        .error(R.drawable.baseline_photo_black_48))
                .into(mImage);

        return view;
    }
}
