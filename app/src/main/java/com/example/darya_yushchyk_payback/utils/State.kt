package com.example.darya_yushchyk_payback.utils

import com.example.domain.model.PhotoModel

sealed interface ViewState {
    data object IdleScreen : ViewState
    data object Loading : ViewState
    data object Error : ViewState
    data object NoResults : ViewState
    data class SearchResultsFetched(val results: List<PhotoModel>) : ViewState
}

sealed class NetworkStatus {
    data object Connected : NetworkStatus()
    data object Disconnected : NetworkStatus()
}

sealed interface PhotoDetailsViewState {
    data object Loading : PhotoDetailsViewState
    data object Error : PhotoDetailsViewState
    data object NoResults : PhotoDetailsViewState
    data class SearchResultsFetched(val results: PhotoModel) : PhotoDetailsViewState
}