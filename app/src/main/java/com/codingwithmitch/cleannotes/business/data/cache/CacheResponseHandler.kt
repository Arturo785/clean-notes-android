package com.codingwithmitch.cleannotes.business.data.cache

import com.codingwithmitch.cleannotes.business.data.cache.CacheErrors.CACHE_DATA_NULL
import com.codingwithmitch.cleannotes.business.domain.state.*


abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>, // the one from the cache
    private val stateEvent: StateEvent? // event received
) {
    suspend fun getResult(): DataState<ViewState>? {

        //Handles all the possible cache responses
        return when (response) {

            /*data class DataState<T>(
                var stateMessage: StateMessage? = null,
                var data: T? = null,
                var stateEvent: StateEvent? = null
                ) {*/

            // returns a stateMessage but no data
            is CacheResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            // returns a stateMessage but no data
            is CacheResult.Success -> {
                // if null is an error
                if (response.value == null) {
                    DataState.error(
                        response = Response(
                            message = "${stateEvent?.errorInfo()}\n\nReason: ${CACHE_DATA_NULL}.",
                            uiComponentType = UIComponentType.Dialog(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                } else {
                    handleSuccess(resultObj = response.value)
                }
            }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}
