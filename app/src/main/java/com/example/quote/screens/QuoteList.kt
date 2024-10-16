package com.example.quote.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quote.DataManager
import com.example.quote.Pages
import com.example.quote.models.Quote

@Composable
fun QuoteList(data: Array<Quote>, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        ) {
        Box (modifier = Modifier.fillMaxWidth().background(Color.Black).padding(10.dp), contentAlignment = Alignment.Center ) {
            Text(
                text = "Quotes",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        LazyColumn(
            content = {
                items (data) {
                    QuoteListItem(quote = it)
                }
            }
        )

    }

}