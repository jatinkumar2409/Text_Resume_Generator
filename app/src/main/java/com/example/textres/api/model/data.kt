package com.example.textres.api.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable


@Serializable
data class Data(
    val address: String,
    val email: String,
    val name: String,
    val phone: String,
    val projects: List<Project>,
    val skills: List<String>,
    val summary: String,
    val twitter: String
)

@Composable
fun FormatText(data: Data? , fontSize : Int) {
    data?.let {
        Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
            Text(text = "Address : " + data.address , fontSize = fontSize.sp)
            Text(text = "Email : " + data.email , fontSize = fontSize.sp)
            Text(text = "Name : " + data.name , fontSize = fontSize.sp)
            Text(text = "Phone : " + data.phone , fontSize = fontSize.sp)
            Text(text = "Twitter : " + data.twitter , fontSize = fontSize.sp)
            Text(text = "\nSkills" , fontSize = (fontSize+2).sp)
            Text("----------------------------------------------")
            data.skills.forEach {
                Text(text = " • $it" , fontSize = fontSize.sp)
            }
            Text(text = "\nProjects" , fontSize = (fontSize + 2).sp)
            Text("----------------------------------------------")
            data.projects.forEach {
                Text(text = "• ${it.title} - ${it.description}" , fontSize = fontSize.sp)
            }
            Text(text = "\n Summary : " + data.summary , fontSize = fontSize.sp)


        }
    }
}