package com.bsuir.weather.presentation.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsuir.weather.R
import com.bsuir.weather.presentation.ui.component.top_bar.TopAppBarWithBackButton
import com.bsuir.weather.presentation.viewmodel.AddressSearchViewModel

@Composable
fun AddressSearchScreen(
    onNavigateToMainClick: () -> Unit,
    modifier: Modifier = Modifier,
    addressSearchViewModel: AddressSearchViewModel = hiltViewModel(),
) {
    val searchText by addressSearchViewModel.searchText.collectAsState()
    val cityResults by addressSearchViewModel.cityResults.collectAsState()

    Scaffold (
        topBar = {
            TopAppBarWithBackButton(
                onBackClick = onNavigateToMainClick,
                title = stringResource(R.string.add_with_name),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { addressSearchViewModel.onSearchTextChanged(it) },
                label = { Text(stringResource(R.string.enter_city_name)) },
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = stringResource(R.string.search)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = Shapes().medium
            )

            if (cityResults.isEmpty() && searchText.isNotBlank()) {
                Text(
                    text = stringResource(R.string.nothing_found),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = cityResults,
                    key = { it.placeId }
                ) { prediction ->
                    val cityName = prediction.getFullText(null).toString()
                    AddressSearchItem(
                        city = cityName,
                        onClick = {
                            addressSearchViewModel.saveLocation(prediction.placeId)
                            onNavigateToMainClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddressSearchItem(
    city: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = Shapes().medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = stringResource(R.string.city_icon),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = city,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = stringResource(R.string.go_forward),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
