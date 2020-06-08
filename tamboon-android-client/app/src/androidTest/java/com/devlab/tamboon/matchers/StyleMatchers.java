package com.devlab.tamboon.matchers;


import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.core.content.ContextCompat;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;



public class StyleMatchers {

    public static Matcher<View> withTextViewTextColor(final int expectedColorResourceId) {
        Checks.checkNotNull(expectedColorResourceId);
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public boolean matchesSafely(TextView warning) {
                int expectedColor = ContextCompat.getColor(warning.getContext(), expectedColorResourceId);
                int currentColor = warning.getCurrentTextColor();
                return expectedColor == currentColor;
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
            }
        };
    }
}
