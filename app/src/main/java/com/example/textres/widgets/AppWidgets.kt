package com.example.textres.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textres.R
import com.example.textres.api.model.Data
import com.example.textres.api.model.FormatText
import com.example.textres.ui.theme.purple
import com.example.textres.ui.theme.purple2
import com.example.textres.ui.theme.purple3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClick : () -> Unit) {

    TopAppBar(title = {
        Text(text = "TextRes")
    } , actions = {
        IconButton(onClick = {
            onClick()
        }) {
            Icon(imageVector = Icons.Default.LocationOn , contentDescription = "Location")

        }
    } , colors = TopAppBarDefaults.topAppBarColors(titleContentColor = Color.White , containerColor = purple , actionIconContentColor = Color.White))
}

@Composable
fun ResumeCard(text: Data?, color: Color = purple3, txtColor : Color = Color.White, fontSize : Int = 16) {
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f)){
        Card(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()) ,colors = CardDefaults.cardColors(containerColor = color , contentColor = txtColor)) {
            if (text == null){
                Text(text = "Loading...")
            }
            else {
                FormatText(text , fontSize)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditButtons(fontSize : MutableState<Int>, color: MutableState<Color> , txtCColor : MutableState<Color>, onTxt : () -> Unit , onBg : () -> Unit){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "FontSize : ${fontSize.value}" , fontSize = 18.sp , color = purple2 , modifier = Modifier.padding(6.dp))
        Slider(value = fontSize.value.toFloat() , onValueChange = { it ->
            fontSize.value = it.toInt()
        } , valueRange = 10f..32f , modifier = Modifier.padding(horizontal = 4.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
            Button(onClick = { onTxt()} , modifier = Modifier.fillMaxWidth().weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(R.drawable.outline_format_color_text_24) , contentDescription = "fontColor")
                    Text(text = "Font Color" , modifier = Modifier.padding(4.dp))
                }
            }
            Spacer(modifier = Modifier.width(1.dp))
            Button(onClick = { onBg()} , modifier = Modifier.fillMaxWidth().weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(R.drawable.outline_format_color_fill_24) , contentDescription = "fontColor")
                    Text(text = "Bg Color" , modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}