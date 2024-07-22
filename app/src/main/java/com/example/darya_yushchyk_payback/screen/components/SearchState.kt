package com.example.darya_yushchyk_payback.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.darya_yushchyk_payback.R
import com.example.darya_yushchyk_payback.screen.ResultList
import com.example.darya_yushchyk_payback.utils.ViewState

@Composable
fun SearchState(
    viewState: ViewState,
    onResultItemClick: (Int) -> Unit,
    modifier: Modifier
) {
    when (viewState) {
        ViewState.IdleScreen -> {
            Column(
                modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_idle_search),
                    contentDescription = "",
                    modifier = modifier.padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.search_idle),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        ViewState.Error -> {
            Column(
                modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "",
                    modifier = modifier.padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.search_error),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        ViewState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color =  MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        ViewState.NoResults -> {
            Column(
                modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_searched_result),
                    contentDescription = "",
                    modifier = modifier.padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.search_empty_result),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        is ViewState.SearchResultsFetched -> {
            ResultList(
                modifier = modifier,
                resultList = viewState.results,
                onResultItemClick = onResultItemClick
            )
        }
    }
}
