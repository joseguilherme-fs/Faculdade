package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    var scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Text(text = "Tela Principal")
        val usuarios = remember { mutableStateListOf<Usuario>() }

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                usuarioDAO.buscar( callback = { usuariosRetornados ->
                    usuarios.clear()
                    usuarios.addAll(usuariosRetornados)
                })
            }
        }) {
            Text("Carregar")
        }
        Button(onClick = { onLogoffClick() }) {
            Text("Sair")
        }

        LazyColumn {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "ID: ${usuario.id}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Nome: ${usuario.nome}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Senha: ${usuario.senha}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}