package com.bsuir.weather.presentation.ui.component.main_screen.weather_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.weather.R
import androidx.compose.foundation.lazy.items

@Composable
fun PopularQuestionsSection(
    onQuestionClick: (String) -> Unit
) {
    val questions = stringArrayResource(R.array.popular_questions).toList()
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.popular_questions),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = questions,
                key = { it }
            ) { question ->
                PopularQuestionChip(text = question, onClick = { onQuestionClick(question) })
            }
        }
    }
}