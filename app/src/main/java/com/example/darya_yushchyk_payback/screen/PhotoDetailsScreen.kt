package com.example.darya_yushchyk_payback.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.darya_yushchyk_payback.R
import com.example.darya_yushchyk_payback.ui.theme.Darya_Yushchyk_PaybackTheme
import com.example.darya_yushchyk_payback.utils.Constants.FULL_PHOTO_FOLDER_NAME
import com.example.darya_yushchyk_payback.utils.PhotoDetailsViewState
import com.example.darya_yushchyk_payback.utils.PhotoDetailsViewState.SearchResultsFetched
import com.example.domain.model.PhotoModel
import java.io.File

@Composable
fun PhotoDetailsScreen(
    modifier: Modifier,
    photoDetailsViewState: PhotoDetailsViewState
) {
    when (photoDetailsViewState) {
        is SearchResultsFetched -> {
            PhotoDetailsContent(
                photoDetailsViewState.results,
                modifier
            )
        }
        else -> {}
    }
}

@Composable
fun PhotoDetailsContent(
    resultItem:PhotoModel,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    File(
                        File(
                            LocalContext.current.filesDir,
                            FULL_PHOTO_FOLDER_NAME
                        ),
                        resultItem.fullImageName
                    )
                )
                .build(),
            contentDescription = "",
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .height(426.dp)
                .width(resultItem.imageWidth.dp)
                .defaultMinSize(50.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = resultItem.user ?: "",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .weight(2f)
                    .padding(start = 8.dp, end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "",
                modifier = modifier
            )
            Text(
                text = resultItem.downloads.toString(),
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier
                    .padding(horizontal = 8.dp)
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = modifier
                .padding(start = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "",
                    modifier = modifier
                )
                Text(
                    text = resultItem.likes.toString(),
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                )
            }
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "",
                    modifier = modifier
                )
                Text(
                    text = resultItem.comments.toString(),
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = resultItem.tags ?: "",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier
                    .padding(horizontal = 8.dp)
                )
        }

    }
}

@Preview()
@Composable
private fun PhotoDetailsContentPreview() {
    Darya_Yushchyk_PaybackTheme {
        PhotoDetailsContent(
            modifier = Modifier,
            resultItem = PhotoModel(
                0, "", "fruit, apple", "",
                0, 0, "https://cdn.pixabay.com/user/2015/05/22/15-05-56-134_250x250.jpg", 0, 0, 0,
                0, 0, "Anna", "", ""
            )
        )
    }
}
