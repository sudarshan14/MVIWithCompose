package com.amlavati.mviwithcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import com.amlavati.mviwithcompose.api.AnimalService
import com.amlavati.mviwithcompose.model.Jokes
import com.amlavati.mviwithcompose.model.JokesList
import com.amlavati.mviwithcompose.presenter.DemoPresenter
import com.amlavati.mviwithcompose.ui.theme.MVIWithComposeTheme
import com.amlavati.mviwithcompose.view.MainIntent
import com.amlavati.mviwithcompose.view.MainState
import com.amlavati.mviwithcompose.view.MainViewModel
import com.amlavati.mviwithcompose.view.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(AnimalService.api)
        )[MainViewModel::class.java]

        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }

        setContent {
            MVIWithComposeTheme {
                val viewModel: DemoPresenter = DemoPresenter()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        vm = mainViewModel,
                        onButtonClick = onButtonClick
                    )

                }
            }
        }
    }
}

@Composable
fun MainScreen(vm: MainViewModel, onButtonClick: () -> Unit) {

    when (val state = vm.state.value) {
        is MainState.Idle -> {
            IdleScreen(onButtonClick)
        }

        MainState.Loading -> {

            LoadingScreen()
        }

        is MainState.Animals -> {
            AnimalList(state.jokes)

        }

        is MainState.Error -> {
            IdleScreen(onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()

        }


    }

}

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Jokes")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun AnimalList(jokes: JokesList) {

    val joke = jokes.jokes
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(items = joke) {
                AnimalItem(it)
                Divider(
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }

        }
    }
}

@Composable
fun AnimalItem(jokes: Jokes) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val url = jokes.image
        val painter = rememberAsyncImagePainter(model = url)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {

            Text(
                text = jokes.title,
                fontWeight = FontWeight.Bold
            )
            Text(text = jokes.message)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillHeight
            )
        }

    }
}


