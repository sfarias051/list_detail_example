package com.example.sebastianfarias.demo.ListDetail;

import com.example.sebastianfarias.demo.ListDetail.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListService {

    @GET("list")
    Call<List<Item>> getListItem();
}
