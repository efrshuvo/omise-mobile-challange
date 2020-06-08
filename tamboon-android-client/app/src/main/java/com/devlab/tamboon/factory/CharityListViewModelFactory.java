package com.devlab.tamboon.factory;

import com.devlab.tamboon.repositories.TamboonRemoteRepository;
import com.devlab.tamboon.viewmodels.CharityListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CharityListViewModelFactory implements ViewModelProvider.Factory{

    private TamboonRemoteRepository tamboonRemoteRepository;

    public CharityListViewModelFactory(TamboonRemoteRepository tamboonRemoteRepository){
        this.tamboonRemoteRepository = tamboonRemoteRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharityListViewModel.class)) {
            return (T) new CharityListViewModel(tamboonRemoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
