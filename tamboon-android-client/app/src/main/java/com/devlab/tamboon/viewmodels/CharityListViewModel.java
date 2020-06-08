package com.devlab.tamboon.viewmodels;

import com.devlab.tamboon.data.CharityListResponse;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CharityListViewModel extends ViewModel {

    private MutableLiveData<CharityListResponse> charityListLiveData;
    private TamboonRemoteRepository tamboonRemoteRepository;

    public CharityListViewModel(TamboonRemoteRepository tamboonRemoteRepository){
        this.tamboonRemoteRepository = tamboonRemoteRepository;
        charityListLiveData = new MutableLiveData<>();
        this.tamboonRemoteRepository.getCharities().observeForever(charityListResponse -> {
            charityListLiveData.setValue(charityListResponse);
        });
    }

    public void requestingCharityList(){
        tamboonRemoteRepository.askingCharities();
    }
    public MutableLiveData<CharityListResponse> getCharityList(){
        return charityListLiveData;
    }
}

