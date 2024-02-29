package com.example.nycschools.ui.schools

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.nycschools.R
import com.example.nycschools.model.School
import com.example.nycschools.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolListScreen(navController: NavController) {
    val viewModel: SchoolListViewModel = hiltViewModel()
    val schools = viewModel.schools.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "NYC Schools", modifier = Modifier.align(Alignment.Center))

                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll((scrollBehavior.nestedScrollConnection))
    ) { paddingValues ->
        SchoolList(
            schools = schools,
            navController = navController,
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}

@Composable
fun SchoolList(
    schools: LazyPagingItems<School>,
    navController: NavController,
    modifier: Modifier,
    context: Context? = null
) {
    LazyColumn(modifier = modifier) {
        items(schools.itemCount) { index ->
            schools[index]?.let { school ->
                SchoolListItem(school = school) {
                    navController.navigate(Routes.schoolDetailRoute(school))
                }
            }
        }

        // Check and display loading state at the end of the list
        schools.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Error -> {
                    item { RetryItem { retry() } }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorItem(
                            errorMessage = (loadState.refresh as LoadState.Error).error.localizedMessage
                                ?: "An error occorred",
                            modifier= Modifier.fillParentMaxSize(),
                            onRetry = {retry()}
                        )
                    }
                }

            }
        }
    }

}

@Composable
fun SchoolListItem(school: School, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.School,
                contentDescription = stringResource(R.string.school_image),
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = school.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Location: ${school.getAddress()}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun ErrorItem(errorMessage: String, modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun RetryItem(onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(onClick = onRetry) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

fun showToastMessage(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}