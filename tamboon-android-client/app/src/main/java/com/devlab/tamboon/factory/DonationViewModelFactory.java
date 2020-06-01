package com.devlab.tamboon.factory;

import com.devlab.tamboon.repositories.TamboonRemoteRepository;
import com.devlab.tamboon.viewmodels.DonationViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DonationViewModelFactory implements ViewModelProvider.Factory{
    private TamboonRemoteRepository tamboonRemoteRepository;

    public DonationViewModelFactory(TamboonRemoteRepository tamboonRemoteRepository){
        this.tamboonRemoteRepository = tamboonRemoteRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DonationViewModel.class)) {
            return (T) new DonationViewModel(tamboonRemoteRepository);

        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
