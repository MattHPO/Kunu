package com.taipei.yanghaobo.kunu

import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.AllOf.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

  @Rule
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun detailActivityTest() {
    val appCompatTextView = onView(
      allOf(
        withId(R.id.tv_dog_name), withText("秋田犬"),
        childAtPosition(
          childAtPosition(
            withId(R.id.card_dog_recycler_item),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatTextView.perform(click())

    val linearLayout = onView(
      allOf(
        withId(R.id.kunu_dog_detail_csl),
        childAtPosition(
          childAtPosition(
            withId(R.id.kunu_main_nav_host_fragment),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    linearLayout.check(matches(isDisplayed()))

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
        return (parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position))
      }
    }
  }
}
