package com.example.nepogoda.infrastructure.helpers

import com.example.nepogoda.infrastructure.models.ResponseType

suspend fun <T> sourceTypeDecider(
    remote: suspend () -> T,
    local: suspend () -> T,
    saveRemoteResponse: (suspend (T) -> Unit)? = null,
    type: ResponseType = ResponseType.REMOTE_OR_LOCAL
): T {
    return when (type) {
        ResponseType.REQUIRE_REMOTE -> {
            requireRemote(remote = remote, saveRemoteResponse = saveRemoteResponse)
        }
        ResponseType.REQUIRE_LOCAL -> {
            requireLocal(local)
        }
        ResponseType.REMOTE_OR_LOCAL -> {
            remoteOrLocal(
                local = local,
                remote = remote,
                saveRemoteResponse = saveRemoteResponse
            )
        }
    }
}

private suspend fun <T> requireRemote(
    remote: suspend () -> T,
    saveRemoteResponse: (suspend (T) -> Unit)?
): T {
    val response = remote()
    if (saveRemoteResponse != null) {
        saveRemoteResponse(response)
    }
    return response
}

private suspend fun <T> requireLocal(local: suspend () -> T): T {
    return local()
}

private suspend fun <T> remoteOrLocal(
    remote: suspend () -> T,
    local: suspend () -> T,
    saveRemoteResponse: (suspend (T) -> Unit)?
): T {
    return try {
        val response = remote()
        if (saveRemoteResponse != null) {
            saveRemoteResponse(response)
        }
        response
    } catch (e: Exception) {
        local()
    }
}