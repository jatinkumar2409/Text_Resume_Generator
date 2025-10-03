package com.example.textres.homeScreen

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.textres.room.Properties
import com.example.textres.ui.theme.PurpleGrey40
import com.example.textres.ui.theme.purple
import com.example.textres.ui.theme.purple2
import com.example.textres.ui.theme.purple3
import com.example.textres.widgets.AppBar
import com.example.textres.widgets.EditButtons
import com.example.textres.widgets.Palette
import com.example.textres.widgets.ResumeCard



@Composable
fun HomeScreen(viewmodel: Viewmodel) {
    val properties by viewmodel.properties.collectAsStateWithLifecycle()
   val coords by viewmodel.location.collectAsStateWithLifecycle()
    val data by viewmodel.data.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var isGrant by rememberSaveable {
        mutableStateOf(ContextCompat.checkSelfPermission(context , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }
     val permissionLaunchPicker = rememberLauncherForActivityResult(
         contract = ActivityResultContracts.RequestPermission()
     ) { isGranted ->
          isGrant = isGranted
     }
    LaunchedEffect(Unit) {
        permissionLaunchPicker.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
 var fontSize = remember {
     mutableStateOf(16)
 }
    Log.d("tag1" , properties?.fontSize.toString())
    var color = remember {
        mutableStateOf(purple3)
    }
    var txtcolor = remember {
        mutableStateOf(Color.White)
    }
          LaunchedEffect(key1 = properties) {
              properties?.let {
                  fontSize.value = it.fontSize
                  color.value = Color("FF${it.color}".toLong(16).toInt())
                  txtcolor.value = Color("FF${it.txtColor}".toLong(16).toInt())
              }
          }
    var visibility by remember {
        mutableStateOf(false)
    }
   var launchPicker by remember {
       mutableStateOf(LaunchPicker())
   }
    Log.d("tag" , visibility.toString())
   if (visibility) {
                Card(
                    modifier = Modifier.size(120.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = PurpleGrey40,
                        contentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Location")
                    }
                }


    }
     if (launchPicker.isClicked){
         if (launchPicker.whichClicked == 0){
             Palette(colorState = txtcolor){
                 launchPicker = launchPicker.copy(isClicked = false)
             }
         }
         else if(launchPicker.whichClicked == 1){
             Palette(colorState = color) {
                 launchPicker = launchPicker.copy(isClicked = false)
             }
         }
     }
    Scaffold(modifier = Modifier.fillMaxSize() , topBar = {
        AppBar {
            visibility = !visibility
        }
    } ){ ip ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip)
        ) {
            Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = " Text Resume Generator" , fontSize = 18.sp , fontWeight = FontWeight.SemiBold , color = purple2)
                Row(verticalAlignment = Alignment.CenterVertically) {
                   IconButton(onClick = {
                       if(properties != null){
                           fontSize.value = properties?.fontSize!!
                           color.value = Color("FF${properties?.color}".toLong(16).toInt())
                           txtcolor.value = Color("FF${properties?.txtColor}".toLong(16).toInt())
                       }

                   } , colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White , containerColor = purple2)) {
              Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack , contentDescription = "undo" )
                   }
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = {
                        properties?.let {
                            viewmodel.deleteProperties(it)
                        }
                        viewmodel.insertProperties(Properties(fontSize = fontSize.value , color = color.value.toArgb().toUInt().toString(16), txtColor = txtcolor.value.toArgb().toUInt().toString(16)))
                        Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show()
                    } , colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White , containerColor = purple2)) {
                        Icon(imageVector = Icons.Default.Check , contentDescription = "Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                }

            }
            ResumeCard(fontSize = fontSize.value , color = color.value , txtColor = txtcolor.value , text = data)
            EditButtons(fontSize = fontSize, color = color, txtCColor = txtcolor , onTxt = {
                launchPicker = LaunchPicker(isClicked = true , whichClicked = 0)
            }){
             launchPicker = LaunchPicker(isClicked = true , whichClicked = 1)
            }
        }
        AnimatedVisibility(visible = visibility, modifier = Modifier.padding(ip) , enter =
            fadeIn() + slideInVertically{ i ->
                -i
            }, exit = fadeOut() + slideOutVertically { i -> -i }
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp) , horizontalArrangement = Arrangement.End) {

                Card(
                    modifier = Modifier.size(height = 120.dp , width = 180.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = purple,
                        contentColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Log.d("tag", coords.toString())
                        Log.d("tag", isGrant.toString())
                        if (isGrant){
                            LaunchedEffect(Unit) {
                                viewmodel.getLocation()
                            }
                             Icon(imageVector = Icons.Default.LocationOn , contentDescription = "location")

                            coords?.let {
                                Column {
                                    Text(text = "Lat ${it.latitude}")
                                    Text(text = "Long ${it.longitude}")
                                }
                            }
                        }else{
                            Text(text = "Grant Permission First!")
                        }
                    }
                }
            }

        }
    }
}
data class LaunchPicker(
    var isClicked : Boolean = false ,
    var whichClicked : Int?  = null
)
