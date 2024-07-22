package com.example.darya_yushchyk_payback.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.darya_yushchyk_payback.screen.components.NetworkState
import com.example.darya_yushchyk_payback.screen.components.SearchState
import com.example.darya_yushchyk_payback.ui.theme.Darya_Yushchyk_PaybackTheme
import com.example.darya_yushchyk_payback.utils.Constants
import com.example.darya_yushchyk_payback.utils.NetworkStatus
import com.example.darya_yushchyk_payback.utils.ViewState
import com.example.domain.model.PhotoModel
import java.io.File

@Composable
fun SearchScreen(
    modifier: Modifier,
    onResultItemClick: (Int) -> Unit,
    viewState: ViewState,
    networkStatus: NetworkStatus,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedPhotoId by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        PhotoSearchField(
            viewState = viewState,
            inputText = inputText,
            onSearchInputChanged = { input -> onSearchInputChanged(input) },
            onResultItemClick = {
                showDialog = true
                selectedPhotoId = it
            },
            networkStatus = networkStatus
        )
    }
    if (showDialog) {
        DialogScreen(
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                showDialog = false
                onResultItemClick(selectedPhotoId)
            },
            modifier = modifier
        )
    }
}

@Composable
fun ResultList(
    modifier: Modifier,
    resultList: List<PhotoModel>,
    onResultItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(resultList) { item ->
            ResultItem(
                resultItem = item,
                modifier = modifier.clickable { onResultItemClick(item.id) }
            )
        }

    }
}

@Composable
fun ResultItem(
    modifier: Modifier,
    resultItem: PhotoModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    File(
                        File(
                            LocalContext.current.filesDir,
                            Constants.PHOTO_PREVIEW_FOLDER_NAME
                        ),
                        resultItem.previewImageName
                    )
                )
                .build(),
            contentDescription = "Translated description of what the image contains",
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .height(resultItem.previewWidth.dp)
                .width(resultItem.previewWidth.dp)
                .padding(horizontal = 14.dp, vertical = 4.dp)
        )
        Column(modifier) {
            Text(
                text = resultItem.user ?: "",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
            )
            Text(
                text = resultItem.tags ?: "",
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun PhotoSearchField(
    viewState: ViewState,
    networkStatus: NetworkStatus,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onResultItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SearchInputField(
            inputText = inputText,
            onSearchInputChanged = onSearchInputChanged,
            networkStatus = networkStatus
        )
        NetworkState(
            networkStatus = networkStatus,
            modifier = Modifier
        )
        SearchState(
            viewState = viewState,
            onResultItemClick = onResultItemClick,
            modifier = Modifier
        )
    }
}

@Preview()
@Composable
private fun PhotoSearchFieldPreview() {
    Darya_Yushchyk_PaybackTheme {
        PhotoSearchField(
            viewState = ViewState.IdleScreen,
            networkStatus = NetworkStatus.Connected,
            inputText = "",
            onSearchInputChanged = {},
            onResultItemClick = {}
        )
    }
}
