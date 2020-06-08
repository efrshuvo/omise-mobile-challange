package com.devlab.tamboon.matchers;

import android.view.View;

import org.hamcrest.Description;

import org.hamcrest.Matcher;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.test.espresso.matcher.BoundedMatcher;

public class SwipeRefreshLayoutMatchers {

    public static Matcher<View> isRefreshing(){
        return new BoundedMatcher<View, SwipeRefreshLayout>(SwipeRefreshLayout.class){
            @Override
            public boolean matchesSafely(SwipeRefreshLayout view) {
                return view.isRefreshing();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is refreshing");
            }
        };
    }
}
