package com.devlab.tamboon.network;

import com.devlab.tamboon.models.CharityList;
import com.devlab.tamboon.models.Donation;
import com.devlab.tamboon.models.DonationResult;

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
