package top.ntutn.common.util

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

interface LifecycleScopeHolder {
    val lifecycleScope: CoroutineScope
}

class LifecycleScopeHelper(lifecycle: Lifecycle): LifecycleScopeHolder {
    private val lifecycleJob = SupervisorJob()
    override val lifecycleScope = CoroutineScope(lifecycleJob + Dispatchers.Main)

    init {
        lifecycle.doOnDestroy {
            lifecycleJob.cancel()
        }
    }
}