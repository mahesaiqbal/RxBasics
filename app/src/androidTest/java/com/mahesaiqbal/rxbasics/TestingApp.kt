package com.mahesaiqbal.rxbasics

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestingApp {

    private lateinit var testingViewModel: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun intValidTesting() {
        testingViewModel = "withEspresso"
    }

    @Test
    fun testAppBehaviour() {
        onView(withId(R.id.btn_install)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.btn_single_joke)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.btn_jokes)).perform(click())
        Thread.sleep(3000)
    }
}