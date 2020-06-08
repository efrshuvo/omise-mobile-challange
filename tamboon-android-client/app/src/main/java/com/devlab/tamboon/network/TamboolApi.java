package com.devlab.tamboon.network;

import com.devlab.tamboon.data.CharityList;
import com.devlab.tamboon.data.Donation;
import com.devlab.tamboon.data.DonationResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TamboolApi {

    @GET("/charities")
    Call<CharityList> getCharityList();

    @POST("/donations")
    Call<DonationResult> makeDonation(@Body Donation donation);

}
