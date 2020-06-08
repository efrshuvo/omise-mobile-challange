package com.devlab.tamboon.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DonationResultViewModel extends ViewModel {

    MutableLiveData<String> donationAmount;

    public DonationResultViewModel(){
        donationAmount = new MutableLiveData<>();
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount.setValue(donationAmount);
    }

    public MutableLiveData<String> getDonationAmount() {
        return donationAmount;
    }
}
