package com.vipin.mygatetest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.content.Intent;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vipin.mygatetest.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by vipin.c on 28/08/2019
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    Intent mIntent;

    @Rule
    public final ActivityTestRule<MainActivity> mMainActivityTestRules = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void shouldBeAbleToFindTheName() {
        onView(withId(R.id.fab)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(17));
    }
}
