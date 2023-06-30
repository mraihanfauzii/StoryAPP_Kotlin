package com.example.submission_awal_intermediate_androidview.stories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainDispatcher(private val test: TestDispatcher = UnconfinedTestDispatcher()): TestWatcher() {

    override fun finished(description: Description) {
        Dispatchers.setMain(test)
    }

    override fun starting(description: Description) {
        Dispatchers.setMain(test)
    }
}