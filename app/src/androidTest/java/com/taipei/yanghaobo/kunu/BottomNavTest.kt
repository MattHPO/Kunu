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
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class BottomNavTest {

  @Rule
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun bottomNavTest() {
    val bottomNavigationItemView = onView(
      allOf(
        withId(R.id.nav_favorite_frag), withContentDescription("最愛的狗狗們"),
        childAtPosition(
          childAtPosition(
            withId(R.id.kunu_main_bottom_nav),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    bottomNavigationItemView.perform(click())

    val bottomNavigationItemView2 = onView(
      allOf(
        withId(R.id.nav_game_frag), withContentDescription("瞭解狗狗"),
        childAtPosition(
          childAtPosition(
            withId(R.id.kunu_main_bottom_nav),
            0
          ),
          2
        ),
        isDisplayed()
      )
    )
    bottomNavigationItemView2.perform(click())

    val bottomNavigationItemView3 = onView(
      allOf(
        withId(R.id.nav_game_frag), withContentDescription("瞭解狗狗"),
        childAtPosition(
          childAtPosition(
            withId(R.id.kunu_main_bottom_nav),
            0
          ),
          2
        ),
        isDisplayed()
      )
    )
    bottomNavigationItemView3.perform(click())

    pressBack()

    val frameLayout = onView(
      allOf(
        withId(R.id.kunu_doglist_collapsingToolbarLayout),
        childAtPosition(
          allOf(
            withId(R.id.appbar),
            childAtPosition(
              IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
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
