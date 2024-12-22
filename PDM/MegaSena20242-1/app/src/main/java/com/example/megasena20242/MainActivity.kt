package com.example.megasena20242

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.megasena20242.modelo.MegaSena
import com.example.megasena20242.ui.theme.MegaSena20242Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaSena20242Theme {
                val megaSena = MegaSena()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumerosSena(
                        megaSena = megaSena,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun NumerosSena(megaSena: MegaSena, modifier: Modifier = Modifier) {
    Log.d("NumerosSena", "serie=${megaSena}")

    var serieApostada by remember {
        mutableStateOf("")
    }
    var mostrarNumeros by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Números da Mega",
            style = MaterialTheme.typography.headlineMedium
        )
        TextField(
            value = serieApostada,
            onValueChange = { serieApostada = it },
            label = { Text("Valor Apostado") },
            placeholder = { Text("Digite os números separados por ,") }
        )
        Button(
            onClick = {
                mostrarNumeros = true
            }
        ) {
            Text("Verificar números")
        }

        // Botão de Redefinir:
        Button(
            onClick = {
                // Redefine os valores:
                serieApostada = ""
                mostrarNumeros = false
            }
        ) {
            Text("Redefinir")
        }

        if (mostrarNumeros) {
            var corAtual = Color.Red
            Row {
                val numerosApostados = serieApostada.split(",").mapNotNull { it.toIntOrNull() }
                for (numero in megaSena.numeros) {
                    if (numerosApostados.contains(numero)) {
                        corAtual = Color.Blue
                    }
                    Text(
                        text = "$numero,",
                        color = corAtual,
                        modifier = modifier
                    )
                    corAtual = Color.Red
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MegaSena20242Theme {
        NumerosSena(megaSena = MegaSena())
    }
}