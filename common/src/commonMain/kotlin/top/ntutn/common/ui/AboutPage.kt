package top.ntutn.common.ui

import AppInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("海瑞待办 ${AppInfo.VERSION_NAME}")
    }
}