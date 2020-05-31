package com.devlab.tamboon.data;

public class DonationResponse {
    private Throwable throwable;
    private DonationResult donationResult;

    public DonationResponse(DonationResult donationResult,Throwable throwable) {
        this.donationResult = donationResult;
        this.throwable = throwable;
    }

    public DonationResult getDonationResult() {
        return donationResult;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
