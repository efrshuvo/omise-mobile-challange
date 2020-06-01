package com.devlab.tamboon.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.devlab.tamboon.R;
import com.devlab.tamboon.databinding.ActivityDonationBinding;
import com.devlab.tamboon.factory.DonationViewModelFactory;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;
import com.devlab.tamboon.viewmodels.DonationViewModel;
import com.squareup.picasso.Picasso;

public class DonationActivity extends AppCompatActivity {

    private ActivityDonationBinding donationBinding;
    private TextView tvCharityName;
    private ImageView ivCharityLogo;
    private TamboonRemoteRepository tamboonRemoteRepository;
    private DonationViewModelFactory donationViewModelFactory;
    private DonationViewModel donationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        donationBinding = ActivityDonationBinding.inflate(getLayoutInflater());
        setContentView(donationBinding.getRoot());
        initView();
        initViewModel();
        configureDonationObserver();
        Intent intent = getIntent();
        donationViewModel.setCharityName(intent.getStringExtra("name"));
        donationViewModel.setCharityLogoUrl(intent.getStringExtra("logoUrl"));
    }

    private void initView(){
        ivCharityLogo = donationBinding.donationCharityLogo;
        tvCharityName = donationBinding.donationCharityName;
    }


    private void initViewModel(){
        tamboonRemoteRepository = TamboonRemoteRepository.getInstance(RetrofitService.getInstance());
        donationViewModelFactory = new DonationViewModelFactory(tamboonRemoteRepository);
        donationViewModel = ViewModelProviders.of(this,donationViewModelFactory).get(DonationViewModel.class);
    }

    private void configureDonationObserver(){
        donationViewModel.getCharityNameLiveData().observe(this,name ->  {
            tvCharityName.setText(name);
        });

        donationViewModel.getCharityLogoUrlLiveData().observe(this,logoUrl ->  {
            Picasso picasso = Picasso.get();
    //        picasso.setIndicatorsEnabled(true);
    //        picasso.setLoggingEnabled(true);
            picasso.load(logoUrl).error(R.drawable.ic_broken_image).fit().into(ivCharityLogo);
        });
    }
}
