package com.devlab.tamboon.views;


import com.devlab.tamboon.R;
import com.devlab.tamboon.data.Charity;
import com.devlab.tamboon.utility.TamboonIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.ContentView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.EditorAction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.devlab.tamboon.matchers.StyleMatchers.matchesBackgroundColor;
import static com.devlab.tamboon.matchers.StyleMatchers.withTextViewTextColor;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DonationActivityTest {
    private final int TEST_ITEM_NUMBER = 2;
    private Charity charity;

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
     *  Check donation title has been loaded properly
     */
    @Test
    public void checkDonationFromTitleLoadProperly(){
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.donation_header_card_view)).check(matches(isDisplayed()));
        onView(withId(R.id.donation_charity_logo)).check(matches(isDisplayed()));
        onView(withId(R.id.donation_charity_name)).check(matches(isDisplayed()));
    }

    /**
     *  Check donation payment form has been loaded properly
     */
    @Test
    public void checkDonationPaymentFormLoadProperly(){
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.donation_form_card_view)).check(matches(isDisplayed()));
        onView(withId(R.id.ll_payment_form)).check(matches(isDisplayed()));
        onView(withId(R.id.et_donation_amount)).check(matches(isDisplayed()));
        onView(withId(R.id.et_card_number)).check(matches(isDisplayed()));
        onView(withId(R.id.et_card_expiry_date)).check(matches(isDisplayed()));
        onView(withId(R.id.et_card_security_code)).check(matches(isDisplayed()));
        onView(withId(R.id.et_card_holder_name)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_pay)).check(matches(isDisplayed()));
    }

    /**
     * Scroll is working when soft key is visible
     */
    @Test
    public void checkScrollIsWorkingWhenSoftKeyIsVisible(){
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.et_donation_amount)).perform(typeText("5645"));
        onView(withId(R.id.sv_donation)).perform(swipeUp());
        onView(withId(R.id.btn_pay)).check(matches(isDisplayed()));
    }

    /**
     *  Check donation payment form validity when fields are empty.
     */
    @Test
    public void checkDonationPaymentFormEmptyFieldValidation()  {
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.tv_input_error)).check(matches(isDisplayed()));
    }

    /**
     *  Check donation payment form input error TextView text color.
     */
    @Test
    public void checkDonationPaymentFormInputErrorTextViewTextColor() {
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.tv_input_error)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_input_error)).check(matches(withTextViewTextColor(R.color.error)));
    }

    /**
     * Check Card Number input field validity.
     */
    @Test
    public void checkDonationPaymentFormCardNumberFieldValidation() {
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.et_donation_amount)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.et_card_number)).perform(typeText("1234567843562345"), closeSoftKeyboard());
        onView(withId(R.id.et_card_expiry_date)).perform(typeText("1020"), closeSoftKeyboard());
        onView(withId(R.id.et_card_security_code)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.et_card_holder_name)).perform(typeText("dfdgfdg"), closeSoftKeyboard());
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.tv_input_error)).check(matches(isDisplayed()));
    }

    /**
     * Check Card Expiry input field validity.
     */
    @Test
    public void checkDonationPaymentFormCardExpiryDateFieldValidation() {
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.et_donation_amount)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.et_card_number)).perform(typeText("4242424242424242"), closeSoftKeyboard());
        onView(withId(R.id.et_card_expiry_date)).perform(typeText("0520"), closeSoftKeyboard());
        onView(withId(R.id.et_card_security_code)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.et_card_holder_name)).perform(typeText("dfdgfdg"), closeSoftKeyboard());
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.tv_input_error)).check(matches(isDisplayed()));
    }

    /**
     * Check Card Expiry input field validity.
     */
    @Test
    public void checkDonationPaymentFormSubmitSuccess() {
        onView(withId(R.id.charity_list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withId(R.id.et_donation_amount)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.et_card_number)).perform(typeText("4242424242424242"), closeSoftKeyboard());
        onView(withId(R.id.et_card_expiry_date)).perform(typeText("1020"), closeSoftKeyboard());
        onView(withId(R.id.et_card_security_code)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.et_card_holder_name)).perform(typeText("dfdgfdg"), closeSoftKeyboard());
        onView(withId(R.id.btn_pay)).perform(click());
        onView(withId(R.id.tv_thanks_message)).check(matches(isDisplayed()));
    }



}
