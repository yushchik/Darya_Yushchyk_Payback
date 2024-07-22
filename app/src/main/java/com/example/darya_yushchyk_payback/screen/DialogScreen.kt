package com.example.darya_yushchyk_payback.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.darya_yushchyk_payback.ui.theme.Darya_Yushchyk_PaybackTheme

@Composable
fun DialogScreen(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "This is a dialog with buttons and an image.",
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = modifier.padding(16.dp),
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = modifier.padding(8.dp),
                    ) {
                        Text(
                            "Dismiss",
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier.padding(8.dp),
                    ) {
                        Text(
                            "Confirm",
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
private fun DialogScreenPreview() {
    Darya_Yushchyk_PaybackTheme {
        DialogScreen(
            onDismissRequest = {},
            onConfirmation = {},
            modifier = Modifier
        )
    }
}