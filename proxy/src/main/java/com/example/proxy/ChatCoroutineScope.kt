package com.example.proxy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

internal object ChatCoroutineScope : CoroutineScope {

    override val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate

}