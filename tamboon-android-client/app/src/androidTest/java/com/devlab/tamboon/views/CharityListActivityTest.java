package com.devlab.tamboon.views;

import com.devlab.tamboon.R;
import com.devlab.tamboon.data.Charity;
import com.devlab.tamboon.data.CharityList;
import com.devlab.tamboon.network.RetrofitService;
import com.devlab.tamboon.network.TamboolApi;
import com.devlab.tamboon.repositories.TamboonRemoteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CharityListActivityTest {


    private final int TEST_ITEM_NUMBER = 2;
    private Charity charity = new Charity();
    private IdlingResource idlingResource;


    @Before
    public void registerIdlingResource() {
        ActivityScenario activityScenario = ActivityScenario.launch(CharityListActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<CharityListActivity>() {
            @Override
            public void perform(CharityListActivity activity) {
                idlingResource = activity.getIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(idlingResource);
            }
        });
    }

    private void loadData(){
        RetrofitService.getInstance().create(TamboolApi.class).getCharityList().enqueue(new Callback<CharityList>() {
            @Override
            public void onResponse(Call<CharityList> call, Response<CharityList> response) {
                charity = response.body().getData().get(TEST_ITEM_NUMBER);
            }

            @Override
            public void onFailure(Call<CharityList> call, Throwable t) {
                t.printStackTrace();
            }
        });
//        charity.setId(2);
//        charity.setName("Paper Ranger");
//        charity.setLogoUrl("https://myfreezer.files.wordpress.com/2007/06/paperranger.jpg");
    }



    /**
     * RecyclerView populates with proper data.
     */
     @Test
    public void recyclerViewLoadedWithProperData() {
         loadData();
         onView(withId(R.id.charity_list_view)).check(ViewAssertions.matches(isDisplayed()));
         onView(withId(R.id.charity_list_view))
                 .perform(RecyclerViewActions.actionOnItemAtPosition(TEST_ITEM_NUMBER, click()));
         onView(withText(charity.getName())).check(matches(isDisplayed()));
     }



    /**
     * Select a list item and navigate to the payment form
     */


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }

}