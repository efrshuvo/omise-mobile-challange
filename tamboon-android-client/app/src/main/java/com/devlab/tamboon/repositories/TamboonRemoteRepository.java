package com.devlab.tamboon.repositories;

import com.devlab.tamboon.data.CharityList;
import com.devlab.tamboon.data.CharityListResponse;
import com.devlab.tamboon.data.Donation;
import com.devlab.tamboon.data.DonationResponse;
import com.devlab.tamboon.data.DonationResult;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.network.TamboolApi;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TamboonRemoteRepository {

    private static RetrofitService retrofitService;
    private MutableLiveData<CharityListResponse> charityListResponseMutableLiveData ;
    private MutableLiveData<DonationResponse> donationResponseMutableLiveData ;
    private TamboolApi tamboolApi;

    private TamboonRemoteRepository(){

        charityListResponseMutableLiveData = new MutableLiveData<>();
        donationResponseMutableLiveData = new MutableLiveData<>();
        tamboolApi = TamboonRemoteRepository.retrofitService.create(TamboolApi.class);
    }

    private static class  InstanceHelper{

        private static final TamboonRemoteRepository REPOSITORY_INSTANCE = new TamboonRemoteRepository();

    }

    public static TamboonRemoteRepository getInstance(RetrofitService retrofitService){
        TamboonRemoteRepository.retrofitService = retrofitService;
        return InstanceHelper.REPOSITORY_INSTANCE;
    }

    public void askingCharities(){
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

    public void sendingDonation(Donation donation){
        tamboolApi.makeDonation(donation).enqueue(new Callback<DonationResult>() {
            @Override
            public void onResponse(Call<DonationResult> call, Response<DonationResult> response) {
                donationResponseMutableLiveData.setValue(new DonationResponse(response.body(),null));
            }

            @Override
            public void onFailure(Call<DonationResult> call, Throwable t) {
                donationResponseMutableLiveData.setValue(new DonationResponse(null,t));
            }
        });
    }

    public MutableLiveData<DonationResponse> getDonationResponse() {
        return donationResponseMutableLiveData;
    }

    public MutableLiveData<CharityListResponse> getCharities(){
        return charityListResponseMutableLiveData;
    }


}
