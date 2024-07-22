package com.example.darya_yushchyk_payback.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darya_yushchyk_payback.R
import com.example.darya_yushchyk_payback.utils.NetworkStatus

@Composable
fun NetworkState(
    networkStatus: NetworkStatus,
    modifier: Modifier
) {
    when (networkStatus) {
        NetworkStatus.Disconnected -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = modifier,
                    text = stringResource(id = R.string.network_disconnected),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        else -> {}
    }
}
