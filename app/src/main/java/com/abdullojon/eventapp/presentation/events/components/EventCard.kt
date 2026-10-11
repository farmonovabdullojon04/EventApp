package com.abdullojon.eventapp.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdullojon.eventapp.domain.model.BroadcastEvent
import com.abdullojon.eventapp.presentation.components.EventIcon
import com.abdullojon.eventapp.ui.theme.CardBackground
import com.abdullojon.eventapp.ui.theme.CardBorder
import com.abdullojon.eventapp.ui.theme.NeonGreen
import com.abdullojon.eventapp.ui.theme.StatusInactiveDot
import com.abdullojon.eventapp.ui.theme.TextGray
import com.abdullojon.eventapp.ui.theme.TextWhite

@Composable
fun EventCard(
    event: BroadcastEvent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = if (event.isOn) NeonGreen else StatusInactiveDot

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            EventIcon(
                iconType = event.iconType,
                tint = if (event.isOn) NeonGreen else TextGray,
                modifier = Modifier.size(26.dp)
            )

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(statusColor)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = event.title,
            color = TextWhite,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = event.statusText,
            color = if (event.isOn) NeonGreen else TextGray,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}
