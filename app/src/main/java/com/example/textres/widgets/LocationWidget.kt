package com.example.textres.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LocationWidget() {
    var latitude by remember {
        mutableStateOf<Double?>(null)
    }
    var longitude by remember {
        mutableStateOf<Double?>(null)
    }

}