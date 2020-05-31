package com.devlab.tamboon.viewmodels;

import com.devlab.tamboon.data.CharityListResponse;
import com.devlab.tamboon.repositories.CharityListRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CharityListViewModel extends ViewModel {

    private MutableLiveData<CharityListResponse> charityListLiveData;
    private CharityListRepository charityListRepository;

    public CharityListViewModel(CharityListRepository charityListRepository){
        this.charityListRepository = charityListRepository;
        charityListLiveData = new MutableLiveData<>();
        this.charityListRepository.getCharityList().observeForever(charityListResponse -> {
            charityListLiveData.setValue(charityListResponse);
        });
    }

    public void requestCharityList(){
        charityListRepository.sendRequest();
    }
    public MutableLiveData<CharityListResponse> getCharities(){
        return charityListLiveData;
    }
}

