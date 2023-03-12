package com.mutkuensert.fitnesspursuit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AutoCompleteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholderText: String? = null,
    searchResults: List<String>
) {
    var searchResultsVisibility by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                searchResultsVisibility = updateSearchResultsVisibility(
                    value = it,
                    searchResults = searchResults
                )
            },
            placeholder = {
                if (placeholderText != null) {
                    Text(text = placeholderText)
                }
            },
            label = {
                if (label != null) {
                    Text(text = label)
                }
            },
            singleLine = true
        )

        AnimatedVisibility(searchResultsVisibility) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 150.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(15.dp)
            ) {
                items(searchResults) {
                    Text(
                        modifier = Modifier.clickable {
                            onValueChange(it)
                            searchResultsVisibility = updateSearchResultsVisibility(
                                value = it,
                                searchResults = searchResults
                            )
                        },
                        text = it
                    )
                }
            }
        }
    }
}

private fun updateSearchResultsVisibility(value: String, searchResults: List<String>): Boolean {
    return when {
        searchResults.size == 1 && value == searchResults[0] -> false
        searchResults.isNotEmpty() && value.isNotEmpty() -> true
        else -> false
    }
}
