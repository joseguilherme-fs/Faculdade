package com.example.navegacao1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navegacao1.ui.telas.TelaCadastro
import com.example.navegacao1.ui.telas.TelaLogin
import com.example.navegacao1.ui.telas.TelaPrincipal
import com.example.navegacao1.ui.theme.Navegacao1Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            Navegacao1Theme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentRoute) {
                                        "login" -> "Login"
                                        "cadastro" -> "Cadastrar"
                                        "principal" -> "Principal"
                                        else -> "App"
                                    }
                                )
                            },
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            TelaLogin(
                                onSigninClick = { navController.navigate("principal") },
                                onCadastroClick = { navController.navigate("cadastro") }
                            )
                        }
                        composable("principal") {
                            TelaPrincipal(onLogoffClick = { navController.navigate("login") })
                        }
                        composable("cadastro") {
                            TelaCadastro(onCadastroSuccess = { navController.navigate("login") })
                        }
                    }
                }
            }
        }
    }
}