package com.example.hospitalmanagementsystem.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val selectedItem = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "EduAfya Hospital",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: handle logout */ }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Logout",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Logout",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Blue) {
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 },
                    icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    label = { Text(text = "Email") }
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Persons") },
                    label = { Text(text = "Person") }
                )
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text(text = "Settings") }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Text(
                text = "Welcome to EduAfya Hospital",
                fontSize = 25.sp,
                color = Color.Blue
            )

            // Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Blue),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "120", color = Color.White, fontSize = 20.sp)
                        Text(text = "Patients", color = Color.White, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Blue),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "45", color = Color.White, fontSize = 20.sp)
                        Text(text = "Doctors", color = Color.White, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier.size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Blue),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "12", color = Color.White, fontSize = 20.sp)
                        Text(text = "Wards", color = Color.White, fontSize = 20.sp)
                    }
                }
            }

            // Card 1 - Add Patient
            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Add Patient",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Add Patient", fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            // Card 2 - Add Doctor
            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Add Doctor",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Add Doctor", fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            // Card 3 - Add Ward
            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Add Ward",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Add Ward", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}