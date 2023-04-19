// ktlint-disable filename
package com.example.nepogoda.infrastructure.async

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.example.nepogoda.coreUI.NepError
import com.example.nepogoda.coreUI.NepLoading

@Composable
fun <T> FullscreenAsyncHandler(
    state: Async<T>,
    onRetryAction: () -> Unit,
    error: @Composable (Throwable) -> Unit = { NepError(onRetryAction = onRetryAction) },
    loading: @Composable () -> Unit = { NepLoading() },
    uninitialized: @Composable () -> Unit = { NepLoading() },
    success: @Composable (T) -> Unit,
) {
    Crossfade(targetState = state) {
        when {
            it is Uninitialized -> uninitialized()
            it is Success || it() != null -> success(requireNotNull(it()))
            it is Loading -> loading()
            it is Fail -> error(it.error)
        }
    }
}
