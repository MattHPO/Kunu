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
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.AllOf.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @Rule
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun mainActivityTest() {
    val frameLayout = onView(
      allOf(
        withId(R.id.card_dog_recycler_item),
        childAtPosition(
          allOf(
            withId(R.id.kunu_dog_recycler),
            childAtPosition(
              IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
              0
            )
          ),
          0
        ),
        isDisplayed()
      )
    )
    frameLayout.check(matches(isDisplayed()))

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
