package com.mahesaiqbal.rxbasics


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uITest() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_install), withText("install"),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_completable_install),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_single_joke), withText("new joke"),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_single_joke),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_jokes), withText("new jokes"),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_observable_jokes),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            2
                        )
                    ),
                    1
                )
            )
        )
        appCompatButton3.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
