package com.example.sebastianfarias.demo.ListDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastianfarias.demo.ListDetail.Model.Item;
import com.example.sebastianfarias.demo.R;
import com.example.sebastianfarias.demo.Utils.Constants;
import com.example.sebastianfarias.demo.Utils.ListRVClick;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class ListFragment extends Fragment implements ListRVClick, SwipeRefreshLayout.OnRefreshListener {
    List<Item> mItems = new ArrayList<>();

    public RecyclerView mRecyclerView;
    public ListAdapter mAdapter;
    public SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        view.setBackgroundColor(getResources().getColor(android.R.color.white));
        getActivity().setTitle(getContext().getString(R.string.title_list));

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.list_swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_purple);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_blue_light);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this.getContext(), HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ListAdapter(mItems, this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setListRVClickListener(this);
        if (mItems.size() == 0){
            loadItems();
        }
        return view;
    }

    private void loadItems(){
        swipeRefreshLayout.setRefreshing(true);
        ListTask listTask = new ListTask();
        listTask.getItems(this);
    }

    @Override
    public void onClick(View view, int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Item item = mAdapter.selectedItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_ITEM, item);
        ListDetailFragment listDetailFragment = new ListDetailFragment();
        listDetailFragment.setArguments(bundle);
        ft.replace(R.id.main_container, listDetailFragment);
        ft.addToBackStack("DETAIL");
        ft.commit();
        fm.executePendingTransactions();
    }

    @Override
    public void onRefresh() {
        loadItems();
    }
}
