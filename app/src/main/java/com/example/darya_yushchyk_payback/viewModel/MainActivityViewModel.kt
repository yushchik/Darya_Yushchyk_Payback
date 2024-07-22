package com.example.darya_yushchyk_payback.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darya_yushchyk_payback.utils.NetworkStatus
import com.example.darya_yushchyk_payback.utils.PhotoDetailsViewState
import com.example.darya_yushchyk_payback.utils.ViewState
import com.example.darya_yushchyk_payback.utils.currentConnectivityState
import com.example.darya_yushchyk_payback.utils.observeConnectivityAsFlow
import com.example.domain.db.GetCachedData
import com.example.domain.db.GetPhotoDetails
import com.example.domain.firstLaunch.FirstLaunchInteractor
import com.example.domain.model.PhotoModel
import com.example.domain.photo.GetPhoto
import com.example.domain.queryValue.QueryValueInteractor
import com.example.domain.util.PhotoDetailsResult
import com.example.domain.util.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getPhoto: GetPhoto,
    private val getCachedPhoto: GetCachedData,
    private val getPhotoDetails: GetPhotoDetails,
    val firstLaunchInteractor: FirstLaunchInteractor,
    val queryValueInteractor: QueryValueInteractor
) : ViewModel() {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    private val _inputText: MutableStateFlow<String> = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val isInitialized = AtomicBoolean(false)

    private val _photoDetailsState =
        MutableStateFlow<PhotoDetailsViewState>(PhotoDetailsViewState.Loading)
    val photoDetailsState: StateFlow<PhotoDetailsViewState> = _photoDetailsState.asStateFlow()

    fun onFirstLaunch() =
        viewModelScope.launch { firstLaunchInteractor.onFirstLaunch() }

    fun updateQueryValue(query: String) =
        viewModelScope.launch { queryValueInteractor.updateQueryValue(query) }

    suspend fun getSavedQuery() =
        _inputText.update { (queryValueInteractor.getQueryValue().first()) }

    @ExperimentalCoroutinesApi
    @Composable
    fun connectivityState(): State<NetworkStatus> {
        val context = LocalContext.current
        return produceState(initialValue = context.currentConnectivityState) {
            context.observeConnectivityAsFlow().collect { value = it }
        }
    }

    @FlowPreview
    fun initialize() {
        if (isInitialized.compareAndSet(false, true)) {
            viewModelScope.launch {
                inputText.debounce(500).collectLatest { query ->
                    if (query.blankOrEmpty()) {
                        _viewState.update { ViewState.IdleScreen }
                        return@collectLatest
                    }

                    when (val result = getPhoto.execute(query = query)) {
                        is SearchResult.Success -> {
                            if (result.results.isEmpty()) {
                                _viewState.update { ViewState.NoResults }
                            } else {
                                _viewState.update { ViewState.SearchResultsFetched(result.results) }
                            }
                        }

                        is SearchResult.Error -> {
                            _viewState.update { ViewState.Error }
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    fun getPhotoDetails(photoId: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getPhotoDetails.execute(photoId)) {
            is PhotoDetailsResult.Success -> {
                _photoDetailsState.update { PhotoDetailsViewState.SearchResultsFetched(result.photoDetails) }
            }

            is PhotoDetailsResult.Error -> {
                _photoDetailsState.update { PhotoDetailsViewState.Error }
            }
        }
    }

    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        if (inputText.blankOrEmpty().not()) {
            _viewState.update { ViewState.Loading }
        }
    }

    fun getCachedData() = viewModelScope.launch(Dispatchers.IO) {
        val result = getCachedPhoto.execute()
        if (result.isEmpty()) {
            _viewState.update { ViewState.NoResults }
        } else {
            _viewState.update { ViewState.SearchResultsFetched(result) }
        }
    }

    private fun String.blankOrEmpty() = this.isBlank() || this.isEmpty()
}
