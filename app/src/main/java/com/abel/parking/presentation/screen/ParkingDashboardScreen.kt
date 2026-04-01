package com.abel.parking.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abel.parking.presentation.components.SpotCard
import com.abel.parking.presentation.model.FinanceUiModel
import com.abel.parking.presentation.viewmodel.ParkingViewModel

enum class DashboardTab {
    GARAGEM,
    FINANCEIRO
}

@Composable
fun ParkingDashboardScreen(
    viewModel: ParkingViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf(DashboardTab.GARAGEM) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Painel do Estacionamento",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                DashboardIconButton(
                    title = "Garagem",
                    selected = selectedTab == DashboardTab.GARAGEM,
                    selectedColor = Color(0xFFEAEAEA),
                    onClick = { selectedTab = DashboardTab.GARAGEM },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = "Garagem",
                            tint = Color(0xFF1565C0),
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    textColor = Color(0xFF1565C0)
                )

                DashboardIconButton(
                    title = "Financeiro",
                    selected = selectedTab == DashboardTab.FINANCEIRO,
                    selectedColor = Color(0xFFEAEAEA),
                    onClick = { selectedTab = DashboardTab.FINANCEIRO },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AttachMoney,
                            contentDescription = "Financeiro",
                            tint = Color(0xFF1B5E20),
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    textColor = Color(0xFF1B5E20)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            when {
                uiState.isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Carregando dados...")
                    }
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.error ?: "Erro desconhecido",
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { viewModel.loadDashboard() }) {
                            Text("Tentar novamente")
                        }
                    }
                }

                else -> {
                    when (selectedTab) {

                        DashboardTab.GARAGEM -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(uiState.spots) { spot ->
                                    SpotCard(spot = spot)
                                }
                            }
                        }

                        DashboardTab.FINANCEIRO -> {
                            FinanceGuardHouse(
                                finance = uiState.finance,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardIconButton(
    title: String,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    textColor: Color
) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = selectedColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon()

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun FinanceGuardHouse(
    finance: FinanceUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AttachMoney,
                contentDescription = null,
                tint = Color(0xFF1B5E20),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Guarita Financeira",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total: R$ %.2f".format(finance.totalAmount),
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(finance.entries) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.DarkGray
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Placa: ${item.plate}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Saída: ${item.exitTime}",
                            color = Color.White
                        )

                        Text(
                            text = "Valor: R$ %.2f".format(item.amount),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}