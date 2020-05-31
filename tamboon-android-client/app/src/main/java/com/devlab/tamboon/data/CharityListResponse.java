package com.devlab.tamboon.data;

import java.util.List;

public class CharityListResponse {
    private Throwable throwable;
    private List<Charity> charityList;

    public CharityListResponse(List<Charity> charityList,Throwable throwable) {
        this.charityList = charityList;
        this.throwable = throwable;
    }

    public List<Charity> getCharityList() {
        return charityList;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
