package com.mutkuensert.gymtools.feature.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import com.mutkuensert.gymtools.navigation.NavigationBuilder
import com.mutkuensert.gymtools.navigation.NavigationDispatcher
import com.mutkuensert.gymtools.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationBuilder: NavigationBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Home(navigationBuilder)
                }
            }
        }
    }

    private fun hideStatusBar() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AppTheme {
        Home(NavigationBuilder(
            navigationDispatcher = NavigationDispatcher(),
            builders = setOf(),
            baseNavigator = object : BaseNavigator {
                override var navController: NavController? = null

                override fun navigateHome() {}

                override fun navigateUp(popUpTo: String?, isInclusive: Boolean) {}

                override fun navigateBack() {}
            }
        ))
    }
}
