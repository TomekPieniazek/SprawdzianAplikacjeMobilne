package com.example.sprawdzian

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherData = listOf(
            WeatherItemModel(R.drawable.ic_snow, "4.0°C", "8:00"),
            WeatherItemModel(R.drawable.ic_cloudy, "4.5°C", "10:00"),
            WeatherItemModel(R.drawable.ic_cloudy, "4.7°C", "12:00"),
            WeatherItemModel(R.drawable.ic_sunny_and_cloudy, "5.0°C", "14:00"),
            WeatherItemModel(R.drawable.ic_sunny_and_cloudy, "5.0°C", "16:00"),
            WeatherItemModel(R.drawable.ic_sunny_and_cloudy, "4.7°C", "18:00")
        )

        setContent {
            WeatherApp(weatherData)
        }
    }
}

data class WeatherItemModel(
    val icon: Int,
    val temperature: String,
    val time: String
)

@Composable
fun WeatherApp(weatherData: List<WeatherItemModel>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF673AB7)) // Purple background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with icon and temperature
        HeaderSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Horizontal weather forecast
        WeatherForecastRow(weatherData)

        Spacer(modifier = Modifier.height(24.dp))

        // Button
        Button(
            onClick = {
                Toast.makeText(context, "Pogoda będzie piękna!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA000), // Orange
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50), // Rounded corners
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sprawdź pozostałe dni", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sunny),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color.Yellow
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "5.0°C",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Warszawa",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}

@Composable
fun WeatherForecastRow(weatherData: List<WeatherItemModel>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF5E35B1), // Slightly darker purple for contrast
                shape = RoundedCornerShape(16.dp) // Rounded corners
            )
            .padding(16.dp) // Padding inside the box
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()), // Enable horizontal scrolling
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            weatherData.forEach { weather ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(weather.icon),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = Color.Unspecified // Preserve original icon colors
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = weather.temperature,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = weather.time,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

