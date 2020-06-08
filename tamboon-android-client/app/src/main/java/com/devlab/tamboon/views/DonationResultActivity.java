package com.devlab.tamboon.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.devlab.tamboon.R;
import com.devlab.tamboon.databinding.ActivityDonationResultBinding;
import com.devlab.tamboon.viewmodels.DonationResultViewModel;

public class DonationResultActivity extends AppCompatActivity {
    private DonationResultViewModel donationResultViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDonationResultBinding donationResultBinding = ActivityDonationResultBinding.inflate(getLayoutInflater());
        setContentView(donationResultBinding.getRoot());
        donationResultViewModel = ViewModelProviders.of(this).get(DonationResultViewModel.class);
        donationResultViewModel.setDonationAmount(getIntent().getStringExtra("donation-amount"));
        donationResultViewModel.getDonationAmount().observe(this,amount ->{
            donationResultBinding.tvThanksMessage.setText(String.format(getResources().getString(R.string.thanks_message),amount));
        });
    }

    public void onDismisClick(View view) {
        finish();
    }
}
