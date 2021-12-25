package com.msk.besinleruygulamas.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) :AndroidViewModel(application),CoroutineScope{

    private val Job =Job()
    override val coroutineContext: CoroutineContext
        get() = Job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        Job.cancel()
    }
}