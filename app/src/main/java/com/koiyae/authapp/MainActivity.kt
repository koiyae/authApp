package com.koiyae.authapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koiyae.authapp.ui.theme.AuthAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AuthScreen(modifier = Modifier.padding(innerPadding), onEnterClick = {user ->
                        Log.i("MainActivity", "Username: ${user.username}, Password: ${user.password}")
                    })
                }
            }
        }
    }
}

@Composable
fun AuthScreen(modifier: Modifier = Modifier, onEnterClick: (User) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = {
                Text(text = "Usuário")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "user")
            }
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(text = "Senha")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
            }
        )

        Button(
            onClick = {
                onEnterClick(
                    User(
                        username,
                        password
                    )
                )
            },
            Modifier
                .padding(10.dp)
                .size(width = 170.dp, height = 50.dp),
        ) {
            Text(text = "Entrar")
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    AuthAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AuthScreen(modifier = Modifier.padding(innerPadding), onEnterClick = {})
        }
    }
}

