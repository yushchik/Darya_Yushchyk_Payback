package com.example.darya_yushchyk_payback.screen

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.darya_yushchyk_payback.R
import com.example.darya_yushchyk_payback.utils.NetworkStatus

@Composable
fun SearchInputField(
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    networkStatus: NetworkStatus
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    TextField(
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        readOnly = networkStatus == NetworkStatus.Disconnected,
        value = inputText,
        onValueChange = { newInput -> onSearchInputChanged(newInput) },
        trailingIcon = {
            Icon(
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.onSecondary,
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Localized description"
            )
        },
        placeholder = {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.search_field),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSecondary
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .focusRequester(focusRequester)
            .focusable(true)
    )
}
