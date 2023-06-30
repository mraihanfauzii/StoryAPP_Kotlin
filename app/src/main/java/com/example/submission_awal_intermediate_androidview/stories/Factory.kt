package com.example.submission_awal_intermediate_androidview.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Factory(private val token: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return StoriesViewModel(Injection.provideRepository("Bearer $token")) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }

}