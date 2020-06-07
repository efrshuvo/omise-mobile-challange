package com.devlab.tamboon.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import co.omise.android.models.Card;
import co.omise.android.models.CardParam;
import co.omise.android.ui.CardNameEditText;
import co.omise.android.ui.CreditCardEditText;
import co.omise.android.ui.ExpiryDateEditText;
import co.omise.android.ui.OmiseEditText;
import co.omise.android.ui.PaymentChooserItem;
import co.omise.android.ui.SecurityCodeEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devlab.tamboon.R;
import com.devlab.tamboon.data.Donation;
import com.devlab.tamboon.data.DonationResponse;
import com.devlab.tamboon.data.DonationResult;
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

    private ProgressBar pbMakeDonation;
    private AmountEditText etAmount;
    private CreditCardEditText etCreditCard;
    private ExpiryDateEditText etExpiryDate;
    private SecurityCodeEditText etSecurityCode;
    private CardNameEditText etCardName;
    private TextView tvInputError;

    private TamboonRemoteRepository tamboonRemoteRepository;
    private DonationViewModelFactory donationViewModelFactory;
    private DonationViewModel donationViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        donationBinding = ActivityDonationBinding.inflate(getLayoutInflater());
        setContentView(donationBinding.getRoot());
        Log.d("Donation","Donation form");
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

        etAmount = donationBinding.etDonationAmount;
        etAmount.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus && tvInputError.getVisibility() == View.VISIBLE){
                    updateEditTextBackground(etAmount);
            }
        });
        etCreditCard = donationBinding.etCardNumber;
        etCreditCard.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus && tvInputError.getVisibility() == View.VISIBLE){
                updateEditTextBackground(etCreditCard);
            }
        });
        etExpiryDate = donationBinding.etCardExpiryDate;
        etExpiryDate.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus && tvInputError.getVisibility() == View.VISIBLE){
                updateEditTextBackground(etExpiryDate);
            }
        });
        etSecurityCode = donationBinding.etCardSecurityCode;
        etSecurityCode.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus && tvInputError.getVisibility() == View.VISIBLE){
                updateEditTextBackground(etSecurityCode);
            }
        });
        etCardName = donationBinding.etCardHolderName;
        etCardName.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus && tvInputError.getVisibility() == View.VISIBLE){
                updateEditTextBackground(etCardName);
            }
        });
        tvInputError = donationBinding.tvInputError;
        pbMakeDonation = donationBinding.pbMakeDonation;
    }




    private void initViewModel(){
        tamboonRemoteRepository = new TamboonRemoteRepository(RetrofitService.getInstance());
        donationViewModelFactory = new DonationViewModelFactory(tamboonRemoteRepository);
        donationViewModel = ViewModelProviders.of(this,donationViewModelFactory).get(DonationViewModel.class);
    }

    private void configureDonationObserver(){
        donationViewModel.getCharityName().observe(this, name ->  {
            tvCharityName.setText(name);
        });

        donationViewModel.getCharityLogoUrl().observe(this, logoUrl ->  {
            Picasso picasso = Picasso.get();
            picasso.load(logoUrl).error(R.drawable.ic_broken_image).fit().into(ivCharityLogo);
        });

        donationViewModel.getCardToken().observe(this, token -> {
            donationViewModel.makeDonation(new Donation(tvCharityName.getText().toString(),token,Integer.parseInt(etAmount.getAmount())));
        });
        donationViewModel.getDonationResult().observe(this,donationResponse -> {
            hideProgressSpinner();
            if(donationResponse.getDonationResult().getSuccess()) {

                goToResultPage();
            }
            else //if not success
            {
                showMessage(donationResponse.getDonationResult().getMessage());
            }

        });

        donationViewModel.getThrowable().observe(this, throwable -> {
            hideProgressSpinner();
            showMessage(throwable.getMessage());
        });
    }

    public void onPayment(View view) {
        updateEditTextBackground(etAmount);
        updateEditTextBackground(etCreditCard);
        updateEditTextBackground(etExpiryDate);
        updateEditTextBackground(etSecurityCode);
        updateEditTextBackground(etCardName);
        requestToGenerateToken();
    }

    private void updateEditTextErrorBackground(OmiseEditText editText){
        if(!editText.isValid()) {
            editText.setBackgroundResource(R.drawable.bg_error_field_outlined);
            tvInputError.setVisibility(View.VISIBLE);
        }
    }

    private void updateEditTextBackground(OmiseEditText editText){
        if(editText.isValid()) {
            hideErrorText();
            editText.setBackgroundResource(R.drawable.bg_edit_text_outlined);
        }else{
            updateEditTextErrorBackground(editText);
        }
    }

    private void hideErrorText(){
        if(tvInputError.getVisibility() == View.VISIBLE) {
            boolean allValid = true;
            if (!etAmount.isValid()) allValid = false;
            if (!etCreditCard.isValid()) allValid = false;
            if (!etExpiryDate.isValid()) allValid = false;
            if (!etSecurityCode.isValid()) allValid = false;
            if (!etCardName.isValid()) allValid = false;
            if (allValid) tvInputError.setVisibility(View.GONE);
        }
    }


    private void requestToGenerateToken(){
        if(tvInputError.getVisibility() == View.GONE){
            showProgressSpinner();
            donationViewModel.generateToken(new CardParam(etCardName.getCardName(),
                    etCreditCard.getCardNumber(), etExpiryDate.getExpiryMonth(),
                    etExpiryDate.getExpiryYear(), etSecurityCode.getSecurityCode(),
                    "",""));
        }
    }

    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    private void goToResultPage(){
        Intent intent = new Intent(this, DonationResultActivity.class);
        intent.putExtra("donation-amount",etAmount.getAmount());
        startActivity(intent);
        finish();
    }

    private void showProgressSpinner(){
        pbMakeDonation.setVisibility(View.VISIBLE);
    }

    private void hideProgressSpinner(){
        pbMakeDonation.setVisibility(View.GONE);
    }


}
