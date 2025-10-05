package com.example.reply.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.currentWindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.windowsizeclass.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.windowsizeclass.ListDetailPaneScaffold
import androidx.compose.material3.windowsizeclass.ListDetailPaneScaffoldRole
import androidx.compose.material3.windowsizeclass.rememberListDetailPaneScaffoldNavigator
import com.example.reply.data.Email

@Composable
fun ReplyApp(
    replyHomeUIState: ReplyHomeUIState,
    onEmailClick: (Email) -> Unit,
) {
    ReplyNavigationWrapperUI {
        ReplyAppContent(
            replyHomeUIState = replyHomeUIState,
            onEmailClick = onEmailClick
        )
    }
}

@Composable
private fun ReplyNavigationWrapperUI(
    content: @Composable () -> Unit = {}
) {
    var selectedDestination: ReplyDestination by remember {
        mutableStateOf(ReplyDestination.Inbox)
    }

    val windowSize = with(LocalDensity.current) {
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass.dp
    }

    val layoutType = if (windowSize >= 1200.dp) {
        NavigationSuiteType.NavigationDrawer
    } else {
        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
    }

    NavigationSuiteScaffold(
        layoutType = layoutType,
        selectedDestination = selectedDestination,
        onDestinationSelected = { selectedDestination = it },
    ) {
        Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ReplyAppContent(
    replyHomeUIState: ReplyHomeUIState,
    onEmailClick: (Email) -> Unit,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Long>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                ReplyListPane(
                    replyHomeUIState = replyHomeUIState,
                    onEmailClick = { email ->
                        onEmailClick(email)
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, email.id)
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                if (replyHomeUIState.selectedEmail != null) {
                    ReplyDetailPane(replyHomeUIState.selectedEmail)
                }
            }
        }
    )
}
