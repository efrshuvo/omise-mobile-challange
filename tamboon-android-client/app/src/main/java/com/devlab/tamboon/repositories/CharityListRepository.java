package com.devlab.tamboon.repositories;

import com.devlab.tamboon.data.CharityList;
import com.devlab.tamboon.data.CharityListResponse;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.network.TamboolApi;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharityListRepository {

    private RetrofitService retrofitService;
    private MutableLiveData<CharityListResponse> charityListResponseMutableLiveData ;
    public CharityListRepository(RetrofitService retrofitService){
        this.retrofitService = retrofitService;
        charityListResponseMutableLiveData = new MutableLiveData<>();
    }

    public void sendRequest(){
        TamboolApi tamboolApi = this.retrofitService.create(TamboolApi.class);
        tamboolApi.getCharityList().enqueue(new Callback<CharityList>() {
            @Override
            public void onResponse(Call<CharityList> call, Response<CharityList> response) {

                charityListResponseMutableLiveData.setValue(new CharityListResponse(response.body().getData(),null));
            }

            @Override
            public void onFailure(Call<CharityList> call, Throwable t) {
                charityListResponseMutableLiveData.setValue(new CharityListResponse(null,t));
            }
        });
    }
    public MutableLiveData<CharityListResponse> getCharityList(){
        return charityListResponseMutableLiveData;
    }


}
