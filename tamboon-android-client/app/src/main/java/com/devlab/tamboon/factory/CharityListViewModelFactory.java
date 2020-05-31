package com.devlab.tamboon.factory;

import com.devlab.tamboon.repositories.CharityListRepository;
import com.devlab.tamboon.viewmodels.CharityListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CharityListViewModelFactory implements ViewModelProvider.Factory {

    private CharityListRepository charityListRepository;

    public CharityListViewModelFactory(CharityListRepository charityListRepository){
        this.charityListRepository = charityListRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharityListViewModel.class)) {
            return (T) new CharityListViewModel(charityListRepository);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
