package com.example.quote.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.ui.graphics.Brush
import com.example.quote.DataManager
import com.example.quote.Pages
import com.example.quote.models.Quote

@Composable
fun QuoteView(quote:Quote?) {
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(
            Brush.sweepGradient(
                colors = listOf(Color.Gray, Color.White)
            )
        )) {
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)) {
        Column(modifier = Modifier.padding(10.dp),) {

            Box (modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {
                Row(modifier = Modifier.clickable { DataManager.currentPage.value = Pages.LISTING; DataManager.currentQuote.value = null }) {
                    Image(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    Text(text = "Back")
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .background(Color.White)
                        .size(20.dp)
                        .rotate(180F)
                )
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .background(Color.White)
                        .size(20.dp)
                        .rotate(0f)
                )
                }

            }

            Spacer(modifier = Modifier.padding(4.dp))
            Column (modifier = Modifier.fillMaxWidth(1f)) {

                Text(
                    text = quote?.author ?: "author",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = quote?.text ?: "author quote",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        }
    }
}

