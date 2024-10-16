package com.example.quote

import android.content.Context
import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.quote.models.Quote
import com.google.gson.Gson

object DataManager {
    var data = emptyArray<Quote>()
    var isLoading = mutableStateOf(true)
    var currentPage =  mutableStateOf(Pages.LISTING);
    var currentQuote = mutableStateOf<Quote?>(null)

    fun  loadAssestsFromFile(context: Context) {
        val inputStrem = context.assets.open("data.json")
        val size :Int = inputStrem.available()
        val buffer = ByteArray(size)

        inputStrem.read(buffer)
        inputStrem.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java);
        isLoading.value = false
    }
    fun switchPage() {
        if (currentPage.value == Pages.LISTING) {
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LISTING
        }
    }
}