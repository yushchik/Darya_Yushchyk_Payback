@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.example.darya_yushchyk_payback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.darya_yushchyk_payback.screen.PhotoDetailsScreen
import com.example.darya_yushchyk_payback.screen.SearchScreen
import com.example.darya_yushchyk_payback.screen.components.PayBackScreen
import com.example.darya_yushchyk_payback.ui.theme.Darya_Yushchyk_PaybackTheme
import com.example.darya_yushchyk_payback.utils.Constants.DEFAULT_QUERY_VALUE
import com.example.darya_yushchyk_payback.utils.Constants.PHOTO_ID_KEY
import com.example.darya_yushchyk_payback.utils.NetworkStatus
import com.example.darya_yushchyk_payback.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Darya_Yushchyk_PaybackTheme {
                val mainActivityViewModel: MainActivityViewModel = hiltViewModel()
                val isFirstLaunch by mainActivityViewModel.firstLaunchInteractor.firstLaunch()
                    .collectAsState(initial = false)

                if (isFirstLaunch) {
                    mainActivityViewModel.updateQueryValue(DEFAULT_QUERY_VALUE)
                    mainActivityViewModel.updateInput(DEFAULT_QUERY_VALUE)
                    mainActivityViewModel.onFirstLaunch()
                }
                LaunchedEffect(key1 = Unit) {
                    mainActivityViewModel.getSavedQuery()
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationSplashActivity(
                        mainActivityViewModel, Modifier
                    )
                }
            }
        }
    }

    @Composable
    fun NavigationSplashActivity(
        mainActivityViewModel: MainActivityViewModel,
        modifier: Modifier = Modifier
    ) {
        val viewState by mainActivityViewModel.viewState.collectAsState()

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PayBackScreen.SearchPhoto.name
        ) {
            composable(PayBackScreen.SearchPhoto.name) {
                val inputText by mainActivityViewModel.inputText.collectAsState()
                val networkStatus by mainActivityViewModel.connectivityState()

                when (networkStatus) {
                    NetworkStatus.Connected -> {
                        LaunchedEffect(key1 = networkStatus) {
                            mainActivityViewModel.initialize()
                        }
                    }

                    NetworkStatus.Disconnected -> {
                        LaunchedEffect(key1 = networkStatus) {
                            mainActivityViewModel.getCachedData()
                        }
                    }
                }
                SearchScreen(
                    modifier = modifier,
                    onResultItemClick = {
                        navController.navigate(
                            PayBackScreen.PhotoDetails.name + "/$it"
                        )
                    },
                    viewState = viewState,
                    networkStatus = networkStatus,
                    inputText = inputText,
                    onSearchInputChanged = {
                        mainActivityViewModel.updateInput(it)
                        mainActivityViewModel.updateQueryValue(it)
                    }
                )

            }

            composable(
                route = "${PayBackScreen.PhotoDetails.name}/{$PHOTO_ID_KEY}",
                arguments = listOf(
                    navArgument("photoId") { type = NavType.IntType }
                )
            )
            { backStackEntry ->
                val photoId = backStackEntry.arguments?.getInt(PHOTO_ID_KEY)
                LaunchedEffect(key1 = Unit) {
                    mainActivityViewModel.getPhotoDetails(photoId ?: 0)
                }
                val photoDetails by mainActivityViewModel.photoDetailsState.collectAsState()

                PhotoDetailsScreen(
                    photoDetailsViewState = photoDetails,
                    modifier = modifier
                )
            }

        }

    }
}
