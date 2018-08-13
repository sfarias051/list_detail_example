package com.example.sebastianfarias.demo.ListDetail;

import android.util.Log;
import android.widget.Toast;

import com.example.sebastianfarias.demo.ListDetail.Model.Item;
import com.example.sebastianfarias.demo.R;
import com.example.sebastianfarias.demo.Utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTask implements Callback<List<Item>>  {
    private ListService listService;
    private ListFragment listFragment;
    public void getItems(ListFragment listFragment){
        this.listFragment = listFragment;
        listService = RetrofitClient.getClient().create(ListService.class);
        Call<List<Item>> call = listService.getListItem();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
        if (response.code() == 200){
            List<Item> itemList = response.body();
            listFragment.mAdapter.addNewItems(itemList);
            listFragment.mItems = itemList;
            setRefresh(false);
        } else {
            showAlert();
            setRefresh(false);
        }
    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        showAlert();
        setRefresh(false);
    }

    private void showAlert(){
        Toast.makeText(listFragment.getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    private void setRefresh(boolean refresh){
        listFragment.swipeRefreshLayout.setRefreshing(refresh);
    }
}
