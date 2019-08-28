package com.vipin.mygatetest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vipin.mygatetest.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by vipin.c on 28/08/2019
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityCameraTest {

    @Rule
    public final IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void shouldTakePhotoFromCameraAndOpenActivity() {


        for (int i = 0; i < 10; i++) {
            Bitmap bitmapIcon = BitmapFactory.decodeResource(
                    InstrumentationRegistry.getTargetContext().getResources(),
                    R.mipmap.ic_launcher_round);

            Intent result = new Intent();
            result.putExtra("data", bitmapIcon);
            Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(
                    Activity.RESULT_OK, result);

            intending(toPackage("com.android.camera2")).respondWith(activityResult);
            onView(withId(R.id.fab)).perform(ViewActions.click());
            intending(toPackage("com.android.camera2"));

        }
    }

}
