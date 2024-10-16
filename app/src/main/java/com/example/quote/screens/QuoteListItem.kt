package com.example.quote.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Icon
import com.example.quote.DataManager
import com.example.quote.Pages
import com.example.quote.models.Quote

@Composable
fun QuoteListItem(quote: Quote) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.clickable {
           DataManager.currentPage.value = Pages.DETAIL;
            DataManager.currentQuote.value = quote;
        }
    ) {
        Row(modifier = Modifier.padding(10.dp),) {
            Image(
                imageVector = Icons.Filled.FormatQuote,
                contentDescription = null,
                alignment = Alignment.TopStart,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .background(Color.Black)
                    .size(40.dp)
                    .rotate(180F)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column (modifier = Modifier.fillMaxWidth(1f)) {
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier
                    .fillMaxWidth(.4f)
                    .background(Color.Gray)
                    .height(1.dp),)
                Text(
                    text = quote.text,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}
