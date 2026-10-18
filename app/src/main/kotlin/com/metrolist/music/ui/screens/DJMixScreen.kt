
package com.metrolist.music.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val MixBackground = Color(0xFF101114)
private val MixCard = Color(0xFF202228)
private val MixAccent = Color(0xFF35E0B1)

@Composable
fun DJMixScreen(
    selectedSongId: String? = null,
    onBack: () -> Unit = {},
) {
    var crossfader by remember { mutableFloatStateOf(0.5f) }
    var volumeA by remember { mutableFloatStateOf(0.8f) }
    var volumeB by remember { mutableFloatStateOf(0.8f) }
    var bass by remember { mutableFloatStateOf(0.5f) }
    var mid by remember { mutableFloatStateOf(0.5f) }
    var treble by remember { mutableFloatStateOf(0.5f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MixBackground)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "MEXAMUSIC",
                    color = MixAccent,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "DJ MIX",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Button(onClick = onBack) {
                Text("Volver")
            }
        }

        Text(
            text = "DOS DECKS · UNA MEZCLA",
            color = Color.LightGray,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DeckCard(
                modifier = Modifier.weight(1f),
                deckName = "DECK A",
                trackName = "Pista A",
                volume = volumeA,
                onVolumeChange = { volumeA = it }
            )
            DeckCard(
                modifier = Modifier.weight(1f),
                deckName = "DECK B",
                trackName = "Pista B",
                volume = volumeB,
                onVolumeChange = { volumeB = it }
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MixCard),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "CROSSFADER",
                    color = MixAccent,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("A", color = Color.White)
                    Text("B", color = Color.White)
                }
                Slider(
                    value = crossfader,
                    onValueChange = { crossfader = it }
                )
                Text(
                    text = "Posición: ${(crossfader * 100).toInt()}%",
                    color = Color.LightGray
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MixCard),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "ECUALIZADOR",
                    color = MixAccent,
                    fontWeight = FontWeight.Bold
                )
                EqualizerControl("Bajos", bass) { bass = it }
                EqualizerControl("Medios", mid) { mid = it }
                EqualizerControl("Agudos", treble) { treble = it }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "MEZCLA MANUAL",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Interfaz inicial. La reproducción, el BPM y las transiciones automáticas se conectarán en siguientes pasos.",
            color = Color.LightGray,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun DeckCard(
    modifier: Modifier = Modifier,
    deckName: String,
    trackName: String,
    volume: Float,
    onVolumeChange: (Float) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MixCard),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = deckName,
                color = MixAccent,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF30353C)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF15171A)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(MixAccent)
                    )
                }
            }
            Text(
                text = trackName,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text("BPM: --", color = Color.LightGray, fontSize = 12.sp)
            Text("Tono: --", color = Color.LightGray, fontSize = 12.sp)
            Text("Volumen", color = Color.White, fontSize = 12.sp)
            Slider(
                value = volume,
                onValueChange = onVolumeChange
            )
        }
    }
}

@Composable
private fun EqualizerControl(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = Color.White
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(2f)
        )
    }
}
