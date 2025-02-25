package mayckgomes.com.projectgym.funcs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object SystemTimer {

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    var timer = MutableStateFlow(false)
    var time = MutableStateFlow(0)

    fun start() {
        if (!timer.value) {
            timer.value = true
            coroutineScope.launch {
                while (timer.value) {
                    delay(1000)
                    time.update { it + 1 }
                }
            }
        }
    }

    fun stop() {
        timer.value = false
    }

    fun clear() {
        time.value = 0
    }
}