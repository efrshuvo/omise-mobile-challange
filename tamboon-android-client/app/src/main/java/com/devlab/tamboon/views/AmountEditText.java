package com.devlab.tamboon.views;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

import org.jetbrains.annotations.Nullable;

import co.omise.android.ui.InputValidationException;
import co.omise.android.ui.OmiseEditText;

public class AmountEditText extends OmiseEditText {

    public AmountEditText(@Nullable Context context) {
        super(context);
    }

    public AmountEditText(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AmountEditText(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    String getAmount(){
        return getText().toString().trim();
    }

    private void init(){
        setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Override
    public void validate() throws InputValidationException {
        super.validate();
        Double.parseDouble(getAmount());
    }


}
