package com.example.learnkotlin.frontend.ui_elements

import android.R.string
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Checkbox(onCheckedChange: (Boolean) -> Unit, text: String) {
    var checkedState by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = {
                    checkedState = it
                    onCheckedChange(it)
                }
            )
            Text(text = text ) // Example label
        }
    }
}