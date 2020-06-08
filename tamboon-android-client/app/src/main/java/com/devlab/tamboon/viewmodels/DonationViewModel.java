package com.devlab.tamboon.viewmodels;

import android.util.Log;

import com.devlab.tamboon.data.Donation;
import com.devlab.tamboon.data.DonationResponse;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import co.omise.android.api.Client;
import co.omise.android.api.Request;
import co.omise.android.api.RequestListener;
import co.omise.android.models.CardParam;
import co.omise.android.models.Token;

public class DonationViewModel extends ViewModel {


    private TamboonRemoteRepository tamboonRemoteRepository;
    private MutableLiveData<String> charityNameLiveData;
    private MutableLiveData<String> charityLogoUrlLiveData;
    private MutableLiveData<String> cardTokenLiveData;
    private MutableLiveData<Throwable> throwableLiveData;

    private MutableLiveData<DonationResponse> donationResponseMutableLiveData;

    private static final String OMISE_PUBLIC_KEY = "pkey_test_5jy4os10d58ajewqsb4";

    public DonationViewModel(TamboonRemoteRepository tamboonRemoteRepository) {
        this.tamboonRemoteRepository = tamboonRemoteRepository;
        charityNameLiveData = new MutableLiveData<>();
        charityLogoUrlLiveData = new MutableLiveData<>();
        cardTokenLiveData = new MutableLiveData<>();
        throwableLiveData = new MutableLiveData<>();
        donationResponseMutableLiveData = new MutableLiveData<>();
        this.tamboonRemoteRepository.getDonationResponse().observeForever(donationResponse -> {
            donationResponseMutableLiveData.setValue(donationResponse);
        });
    }

    public void setCharityName(String charityName) {
        Log.d("Donation","Charity Name = "+charityName);
        if(charityName!=null && !charityName.isEmpty()) {
            this.charityNameLiveData.setValue(charityName);
            Log.d("Donation","Charity Name = "+charityName);
        }
    }

    public void setCharityLogoUrl(String charityLogoUrl) {
        Log.d("Donation","Charity url = "+charityLogoUrl);
        if(charityLogoUrl!=null && !charityLogoUrl.isEmpty()) {
            this.charityLogoUrlLiveData.setValue(charityLogoUrl);
            Log.d("Donation","Charity url = "+charityLogoUrl);
        }
    }

    public MutableLiveData<String> getCharityName() {
        return charityNameLiveData;
    }

    public MutableLiveData<String> getCharityLogoUrl() {
        return charityLogoUrlLiveData;
    }

    public MutableLiveData<String> getCardToken() {
        return cardTokenLiveData;
    }

    public MutableLiveData<Throwable> getThrowable() {
        return throwableLiveData;
    }

    public MutableLiveData<DonationResponse>getDonationResult(){
        return donationResponseMutableLiveData;
    }

    public void generateToken(CardParam cardParam){
        Client client = new Client(OMISE_PUBLIC_KEY);
        Request<Token> request = new Token.CreateTokenRequestBuilder(cardParam).build();
        client.send(request, new RequestListener<Token>() {
            @Override
            public void onRequestSucceed(@NotNull Token token) {
                cardTokenLiveData.setValue(token.getId());
            }

            @Override
            public void onRequestFailed(@NotNull Throwable throwable) {
                throwableLiveData.setValue(throwable);
            }
        });
    }

    public void makeDonation(Donation donation){
        tamboonRemoteRepository.sendingDonation(donation);
    }


}
