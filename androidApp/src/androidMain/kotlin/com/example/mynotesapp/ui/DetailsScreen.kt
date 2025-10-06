package com.example.mynotesapp.ui

import android.graphics.drawable.Icon
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotesapp.viewModels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen (onBack: () -> Unit,
                   onDelete: () -> Unit,
                   detailsViewModel: DetailsViewModel = DetailsViewModel(0.toString())){

    val title by detailsViewModel.title.collectAsState()
    val content by detailsViewModel.content.collectAsState()

    BackHandler {
        detailsViewModel.back()
        onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,

        ){
            IconButton(
                onClick = {
                    detailsViewModel.back()
                    onBack()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Check, // icono de "tick"
                    contentDescription = "Save",
                    tint = Color(0xFF95A66D)          // color del icono
                )
            }
            IconButton(
                onClick = {
                    detailsViewModel.backDelete()
                    onDelete()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Close, // icono de "tick"
                    contentDescription = "Delete",
                    tint = Color.Red        // color del icono
                )
            }
        }

//        Spacer(modifier = Modifier.height(26.dp))
        TextField(
            value = title,
            onValueChange = { detailsViewModel.onTitleChanged(it) },
            placeholder = { Text(
                text="Title",
                color = Color.Gray.copy(alpha = 0.6f),
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif,
            ) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                // --- Colores del contenedor (el fondo) ---
                focusedContainerColor = Color.White.copy(alpha = 0.2f), // Fondo cuando está enfocado
                unfocusedContainerColor = Color.White.copy(alpha = 0.1f), // Fondo cuando no está enfocado
                disabledContainerColor = Color.Gray.copy(alpha = 0.1f), // Fondo cuando está deshabilitado
                errorContainerColor = Color.Red.copy(alpha = 0.1f), // Fondo en estado de error

                // --- Colores del texto y cursor ---
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black.copy(alpha = 0.8f),
                cursorColor = Color.Black,

                // --- Colores del indicador (la línea inferior) ---
                focusedIndicatorColor = Color.Transparent,    // Sin línea al enfocar
                unfocusedIndicatorColor = Color.Transparent,  // Sin línea al no estar enfocado
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red // Línea roja en estado de error
            )
        )
//        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = content,
            onValueChange = { detailsViewModel.onContentChanged(it) },
//            modifier = modifier,
//            label = { Text("Introduce texto") },
            placeholder = { Text(
                text="Content",
                color = Color.Gray.copy(alpha = 0.6f),
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
            ) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                // --- Colores del contenedor (el fondo) ---
                focusedContainerColor = Color.White.copy(alpha = 0.2f), // Fondo cuando está enfocado
                unfocusedContainerColor = Color.White.copy(alpha = 0.1f), // Fondo cuando no está enfocado
                disabledContainerColor = Color.Gray.copy(alpha = 0.1f), // Fondo cuando está deshabilitado
                errorContainerColor = Color.Red.copy(alpha = 0.1f), // Fondo en estado de error
                // --- Indicator Colors ---
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red // Línea roja en estado de error
            ),
        )
    }
}

@Preview(showBackground=true)
@Composable
fun DetailsScreenPrev() {
    DetailsScreen({}, onDelete = {})
}