package com.devlab.tamboon.viewmodels;

import android.util.Log;

import com.devlab.tamboon.data.DonationResponse;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DonationViewModel extends ViewModel {


    private TamboonRemoteRepository tamboonRemoteRepository;
    private MutableLiveData<String> charityNameLiveData;
    private MutableLiveData<String> charityLogoUrlLiveData;
    private MutableLiveData<DonationResponse> donationResponseMutableLiveData;

    public DonationViewModel(TamboonRemoteRepository tamboonRemoteRepository) {
        this.tamboonRemoteRepository = tamboonRemoteRepository;
        charityNameLiveData = new MutableLiveData<>();
        charityLogoUrlLiveData = new MutableLiveData<>();
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

    public MutableLiveData<String> getCharityNameLiveData() {
        return charityNameLiveData;
    }

    public MutableLiveData<String> getCharityLogoUrlLiveData() {
        return charityLogoUrlLiveData;
    }

    public MutableLiveData<DonationResponse>getDonationResult(){
        return donationResponseMutableLiveData;
    }


}
