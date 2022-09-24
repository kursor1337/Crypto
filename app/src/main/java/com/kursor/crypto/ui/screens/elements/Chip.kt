package com.kursor.crypto.ui.screens.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> Chip(
    modifier: Modifier = Modifier,
    element: T,
    isSelected: Boolean = false,
    onSelectionChanged: (T) -> Unit = {},
    transformToString: (T) -> String
) {
    Surface(
        modifier = modifier.padding(4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colors.primaryVariant else Color.LightGray
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(element)
                }
            )
        ) {
            Text(
                text = transformToString(element),
                style = MaterialTheme.typography.body2,
                color = if (isSelected) MaterialTheme.colors.primary else Color.DarkGray,
                modifier = Modifier.padding(
                    vertical = 4.dp,
                    horizontal = 20.dp
                )
            )
        }
    }
}

@Composable
fun <T> ChipGroup(
    elements: List<T>,
    selected: T? = null,
    onSelectedChanged: (T) -> Unit = {},
    transformToString: (T) -> String = { it.toString() }
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(elements) {
                Chip(
                    element = it,
                    isSelected = selected == it,
                    onSelectionChanged = {
                        onSelectedChanged(it)
                    },
                    transformToString = transformToString
                )
            }
        }
    }
}