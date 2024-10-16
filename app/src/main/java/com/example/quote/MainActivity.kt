package com.example.quote
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quote.ui.theme.QuoteTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssestsFromFile(applicationContext)
        }
        setContent {
            QuoteTheme {
                MainApp()
            }
        }
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun MainApp() {
    val systemUiController = rememberSystemUiController()
    val vm = TodoViewModel()

    val customGreen = Color.Black // Hex color for a custom green
    systemUiController.setStatusBarColor(color = customGreen, darkIcons = false)
//    QuoteTheme {
//        Scaffold (modifier = Modifier) {
//            if (isLoading.value) {
//                Box(modifier = Modifier
//                    .background(Color.White)
//                    .fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("Loading...")
//                }
//            } else {
//                if (DataManager.currentPage.value == Pages.LISTING) {
//                    QuoteList(data = DataManager.data, modifier = Modifier.padding(it))
//                } else {
//                    QuoteView(quote = DataManager.currentQuote.value)
//                }
//            }
//        }
//    }
    TodoView(vm)
}
enum class Pages {
    LISTING,
    DETAIL
}
@ExperimentalMaterial3Api
@Composable
fun TodoView(vm: TodoViewModel) {

    LaunchedEffect(Unit) {
        vm.getTodoList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Todos")
                    }
                })
        },
       content =  {
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(it)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(vm.todoList) { todo ->
                            Item(todo)
                        }
                    }
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}

@Composable
fun Item(todo: Todo) {

    var isChecked by rememberSaveable(todo.id) { mutableStateOf(todo.completed) }

    fun updateValue(checked: Boolean) {
        isChecked = checked
        Log.d("Todo", "Todo item updated: $todo with completed status: $checked")
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
            ) {
                Text(
                    todo.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isChecked,
                onCheckedChange = { updateValue(it) }
            )
        }
        Divider()
    }
}

