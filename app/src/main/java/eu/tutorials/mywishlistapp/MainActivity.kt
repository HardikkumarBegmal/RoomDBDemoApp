package eu.tutorials.mywishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    var showBottomSheet = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoading = remember { mutableStateOf(false) }
            DefaultLayoutScreen(
                title = "My Wish List",
                backGroundColor = Color.White,
                isLoading = isLoading,
                isError = mutableStateOf(false),
                isBottomSheetVisible = mutableStateOf(true),
                logoutAction = {
                    isLoading.value = true
                }) {
                Button(onClick = { showBottomSheet.value = true }) {
                    Text(text = "Open Bottom Sheet")
                }

                if (showBottomSheet.value) {
                    TestingModalBottomSheet(showBottomSheet, isLoading)
                }
            }
            /*MyWishListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }*/
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestingModalBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    isLoading: MutableState<Boolean>
) {
    val sheetState = rememberModalBottomSheetState(true, confirmValueChange = { it != SheetValue.Hidden })
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch { showBottomSheet.value = false }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("This is a modal bottom sheet.")
            Button(onClick = {
                scope.launch {
                    isLoading.value = true
                    // showBottomSheet.value = false
                }
            }) {
                Text("Close Sheet")
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            scope.launch { sheetState.show() }
        }) {
            Text("Open Bottom Sheet")
        }
    }
}
