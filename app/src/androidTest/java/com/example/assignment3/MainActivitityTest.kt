package com.example.assignment3

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkAddButtonDisplayed() {
        // Check if the "Add Game" button is displayed
        onView(withId(R.id.buttonAdd))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewIsDisplayed() {
        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun addButtonOpensAddGameActivity() {
        // Simulate click on Add button and check if it opens AddGameActivity
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.addGameLayout)) // Assuming AddGameActivity has a layout with id `addGameLayout`
            .check(matches(isDisplayed()))
    }
}

