package com.example.abu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD8EAE3) // cor de fundo
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo React
        Image(
            painter = painterResource(id = R.drawable.react_logo),
            contentDescription = "React Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Fit
        )

        // Nome
        Text(
            text = "Italo Braz",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "React Developer Extraordinaire",
            fontSize = 16.sp,
            color = Color(0xFF3DDC84),
            modifier = Modifier.padding(top = 8.dp, bottom = 40.dp)
        )

        ContactInfo()
    }
}

@Composable
fun ContactInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ContactRow(R.drawable.ic_phone, "+55 (81) 98520-0745")
        ContactRow(R.drawable.ic_share, "@jtalobraz")
        ContactRow(R.drawable.ic_email, "dev.italobraz@gmail.com")
    }
}

@Composable
fun ContactRow(iconId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color(0xFF3DDC84),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
    }
}
