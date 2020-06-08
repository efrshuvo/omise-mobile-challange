package com.devlab.tamboon.utility;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class TamboonIdlingResource{

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource("tamboon_idling_resource");
    public static void increment() {
        mCountingIdlingResource.increment();
    }
    public static void decrement() {
        mCountingIdlingResource.decrement();
    }
    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}