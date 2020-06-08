package com.devlab.tamboon.views;

import com.devlab.tamboon.R;
import com.devlab.tamboon.data.Charity;
import com.devlab.tamboon.data.CharityList;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.network.TamboolApi;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;


import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.devlab.tamboon.matchers.SwipeRefreshLayoutMatchers.isRefreshing;


@RunWith(AndroidJUnit4ClassRunner.class)
public class CharityListActivityTest {


    private final int TEST_ITEM_NUMBER = 2;
    private Charity charity;

    @Rule
    public ActivityScenarioRule<CharityListActivity> activityTestRule =
            new ActivityScenarioRule<>(CharityListActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(TamboonIdlingResource.getIdlingResource());
        loadData();
    }

    private void loadData(){
        TamboonIdlingResource.increment();
        RetrofitService.getInstance().create(TamboolApi.class).getCharityList().enqueue(new Callback<CharityList>() {
            @Override
            public void onResponse(Call<CharityList> call, Response<CharityList> response) {
                charity = response.body().getData().get(TEST_ITEM_NUMBER);
                TamboonIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<CharityList> call, Throwable t) {
                TamboonIdlingResource.decrement();
                t.printStackTrace();
            }
        });
    }



    /**
     * RecyclerView populates with proper data.
     */
    @Test
    public void recyclerViewLoadedWithProperData() {
        onView(withId(R.id.charity_list_view)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.charity_list_view)).perform(RecyclerViewActions.scrollToPosition(TEST_ITEM_NUMBER));
        onView(withText(charity.getName())).check(matches(isDisplayed()));
    }

    /**
     * SwipeRefreshLayout perform refresh during pull to refresh
      */
    @Test
    public void pullToRefreshPerformRefresh(){
        onView(withId(R.id.pul_to_refresh_layout)).perform(swipeDown());
        onView(withId(R.id.pul_to_refresh_layout)).check(matches(isRefreshing()));
    }


    /**
     * Item click operation.
     */
    @Test
    public void recyclerViewPerformItemClick() {
        onView(withId(R.id.charity_list_view))
                 .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
        onView(withText(charity.getName())).check(matches(isDisplayed()));
    }


    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(TamboonIdlingResource.getIdlingResource());
    }



}