@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.AC3CalcNota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ac3calcnota.ui.theme.AC3CalcNotaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AC3CalcNotaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculaNota()
                }
            }
        }
    }
}

@Composable
fun CalculaNota() {
    var valorAC1 by remember { mutableStateOf("") }
    var valorAC2 by remember { mutableStateOf("") }
    var valorAC3 by remember { mutableStateOf("") }
    var valorAC4 by remember { mutableStateOf("") }
    var valorAC5 by remember { mutableStateOf("") }
    var valorPAI1 by remember { mutableStateOf("") }
    var valorPAI2 by remember { mutableStateOf("") }
    var valorPAI3 by remember { mutableStateOf("") }
    var ac1 by remember { mutableStateOf(0.0) }
    var ac2 by remember { mutableStateOf(0.0) }
    var ac3 by remember { mutableStateOf(0.0) }
    var ac4 by remember { mutableStateOf(0.0) }
    var ac5 by remember { mutableStateOf(0.0) }
    var pai1 by remember { mutableStateOf(0.0) }
    var pai2 by remember { mutableStateOf(0.0) }
    var pai3 by remember { mutableStateOf(0.0) }

    var outputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Inputs
        TextField(
            value = valorAC1,
            onValueChange = { valorAC1 = it },
            label = { Text("Nota da AC1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorAC2,
            onValueChange = { valorAC2 = it },
            label = { Text("Nota da AC2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorAC3,
            onValueChange = { valorAC3 = it },
            label = { Text("Nota da AC3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorAC4,
            onValueChange = { valorAC4 = it },
            label = { Text("Nota da AC4") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorAC5,
            onValueChange = { valorAC5 = it },
            label = { Text("Nota da AC5") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorPAI1,
            onValueChange = { valorPAI1 = it },
            label = { Text("Nota da PAI1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorPAI2,
            onValueChange = { valorPAI2 = it },
            label = { Text("Nota da PAI2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = valorPAI3,
            onValueChange = { valorPAI3 = it },
            label = { Text("Nota da PAI3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        // Botão para calcular
        Button(
            onClick = {
                ac1 = valorAC1.toDoubleOrNull() ?: 0.0
                ac2 = valorAC2.toDoubleOrNull() ?: 0.0
                ac3 = valorAC3.toDoubleOrNull() ?: 0.0
                ac4 = valorAC4.toDoubleOrNull() ?: 0.0
                ac5 = valorAC5.toDoubleOrNull() ?: 0.0
                pai1 = valorPAI1.toDoubleOrNull() ?: 0.0
                pai2 = valorPAI2.toDoubleOrNull() ?: 0.0
                pai3 = valorPAI3.toDoubleOrNull() ?: 0.0

                val output = calcularEFormatarResultado(ac1, ac2, ac3, ac4, ac5, pai1, pai2, pai3)
                outputText = output
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calcular")
        }

        // Saída
        Text(outputText)
    }
}

private fun calcularEFormatarResultado(
    ac1: Double, ac2: Double, ac3: Double, ac4: Double, ac5: Double,
    pai1: Double, pai2: Double, pai3: Double
): String {
    val mediaParcial = (ac1 + ac2) / 2
    val mediaAC = (ac1 + ac2 + ac3) / 3
    val mediaPAI = (pai1 + pai2 + pai3) / 3

    val resultado = formulaNormal(mediaAC, 0.0, 1.0)

    return "Média Parcial: $mediaParcial\nMédia AC: $mediaAC\nMédia PAI: $mediaPAI\nResultado: $resultado"
}

@Composable
fun formulaNormal(mediaac: Double, notaprova: Double, pontoextra: Double): Double {
    return (mediaac * 0.5) + (notaprova * 0.5) + pontoextra
}

@Preview(showBackground = true)
@Composable
fun CalculaNotaPreview() {
    AC3CalcNotaTheme {
        CalculaNota()
    }
}
