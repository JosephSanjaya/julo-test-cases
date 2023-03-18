package julotestcase.sanjaya.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

val LifecycleOwner.trueLifecycle
    get() = when (this) {
        is Fragment -> viewLifecycleOwner.lifecycle
        else -> lifecycle
    }

val LifecycleOwner.trueLifecycleScope
    get() = when (this) {
        is Fragment -> viewLifecycleOwner.lifecycleScope
        else -> lifecycleScope
    }

/**
 * @param block
 */
fun LifecycleOwner.repeatOnStarted(
    block: suspend () -> Unit
) {
    trueLifecycleScope.launch {
        trueLifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}
