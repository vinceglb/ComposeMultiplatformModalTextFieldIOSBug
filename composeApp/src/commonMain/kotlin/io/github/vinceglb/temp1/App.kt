package io.github.vinceglb.temp1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var textBuggy by remember { mutableStateOf("") }

    MaterialTheme {
        AppContent(
            textBuggy = textBuggy,
            onUpdateTextBuggy = { textBuggy = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(
    textBuggy: String,
    onUpdateTextBuggy: (String) -> Unit,
) {
    var textWorking by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Button(onClick = { showBottomSheet = true }) {
                Text("Click me!")
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            ModalContent(
                textBuggy = textBuggy,
                onUpdateTextBuggy = onUpdateTextBuggy,
                textWorking = textWorking,
                onUpdateTextWorking = { textWorking = it }
            )
        }
    }
}

@Composable
fun ModalContent(
    textBuggy: String,
    onUpdateTextBuggy: (String) -> Unit,
    textWorking: String,
    onUpdateTextWorking: (String) -> Unit,
) {
    Column {
        Text("Working - Text:\"${textWorking}\"")
        TextField(
            value = textWorking,
            onValueChange = onUpdateTextWorking
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Buggy - Text:\"${textBuggy}\"")
        TextField(
            value = textBuggy,
            onValueChange = onUpdateTextBuggy
        )
    }
}
