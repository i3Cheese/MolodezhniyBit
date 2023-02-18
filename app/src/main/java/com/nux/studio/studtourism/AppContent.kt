package com.nux.studio.studtourism

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.nux.studio.studtourism.ui.navigation.AppNavGraph
import com.nux.studio.studtourism.ui.navigation.SegmentControllBar
import com.nux.studio.studtourism.ui.navigation.SegmentControlTabs
import com.nux.studio.studtourism.ui.theme.StudTourismTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppContent() {
    StudTourismTheme {
        val tabs = remember { SegmentControlTabs.values() }
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SegmentControllBar(
                    navController = navController,
                    tabs = tabs
                )
            }
        ) {
            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}