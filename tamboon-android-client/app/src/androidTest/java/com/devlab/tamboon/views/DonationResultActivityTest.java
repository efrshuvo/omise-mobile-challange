package com.devlab.tamboon.views;


import com.devlab.tamboon.R;
import com.devlab.tamboon.utility.TamboonIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DonationResultActivityTest {

    private final int TEST_ITEM_NUMBER = 2;

    @Rule
    public ActivityScenarioRule<CharityListActivity> activityTestRule =
            new ActivityScenarioRule<>(CharityListActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(TamboonIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(TamboonIdlingResource.getIdlingResource());
    }

    /**
     * Check if appear first screen after pressing dismiss button
     */
    @Test
    public void checkFirstScreenIsAppearAfterPressingDismissButton(){
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.et_donation_amount)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.et_card_number)).perform(typeText("4242424242424242"), closeSoftKeyboard());
        onView(withId(R.id.et_card_expiry_date)).perform(typeText("1020"), closeSoftKeyboard());
        onView(withId(R.id.et_card_security_code)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.et_card_holder_name)).perform(typeText("UI TEST"), closeSoftKeyboard());
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.bt_dismiss)).perform(click());
        onView(withId(R.id.charity_list_view)).check(ViewAssertions.matches(isDisplayed())); // first page charity list view
    }
}
