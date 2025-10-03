package com.example.textres.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.textres.ui.theme.purple2
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


@Composable
fun Palette(colorState : MutableState<Color> , onDismiss : () -> Unit) {

    Dialog(onDismissRequest = {}) {
        var color by remember {
            mutableStateOf(ColorPicker())
        }
        val controller = rememberColorPickerController()
        Column(modifier = Modifier.fillMaxWidth().background(color = Color.Transparent)) {
            Row(modifier = Modifier.align(Alignment.End)) {
                IconButton(onClick = {
                    onDismiss()
                },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White,
                        containerColor = purple2
                    )) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "undo"
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(onClick = {
                    colorState.value = Color("FF${color.hexcode.removePrefix("#")}".toLong(16).toInt())
                    onDismiss()
                },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White,
                        containerColor = purple2
                    )) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
                }
                Spacer(modifier = Modifier.width(8.dp))

            }
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(10.dp),
                controller = controller,
                onColorChanged = { colorEnvelope ->
                    color =
                        ColorPicker(color = colorEnvelope.color, hexcode = colorEnvelope.hexCode)
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(16.dp).background(
                        color = Color(
                            "FF${color.hexcode.removePrefix("#")}".toLong(16).toInt()
                        )
                    )
                )
                Text(
                    text = "#${color.hexcode}",
                    modifier = Modifier.padding(4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =
                        Color.White
                )
            }

        }
    }

}
data class ColorPicker(
    var color : Color? = null , var hexcode : String = "" )
