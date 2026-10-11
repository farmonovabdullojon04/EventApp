package com.abdullojon.eventapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.BatterySaver
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.DataSaverOn
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abdullojon.eventapp.domain.model.EventIconType

@Composable
fun EventIcon(
    iconType: EventIconType,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    val imageVector = when (iconType) {
        EventIconType.WIFI -> Icons.Default.Wifi
        EventIconType.BLUETOOTH -> Icons.Default.Bluetooth
        EventIconType.AIRPLANE_MODE -> Icons.Default.Flight
        EventIconType.INTERNET -> Icons.Default.Public
        EventIconType.LOCATION -> Icons.Default.LocationOn
        EventIconType.CHARGING -> Icons.Default.ElectricBolt
        EventIconType.HEADSET -> Icons.Default.Headset
        EventIconType.BATTERY_SAVER -> Icons.Default.BatterySaver
        EventIconType.DO_NOT_DISTURB -> Icons.Default.DoNotDisturbOn
        EventIconType.SILENT -> Icons.AutoMirrored.Filled.VolumeOff
        EventIconType.NFC -> Icons.Default.Nfc
        EventIconType.DATA_SAVER -> Icons.Default.DataSaverOn
        EventIconType.BATTERY_LOW -> Icons.Default.BatteryAlert
        EventIconType.USB -> Icons.Default.Usb
        EventIconType.POWER_CONNECTED -> Icons.Default.Power
        EventIconType.SCREEN_ON -> Icons.Default.WbSunny
        EventIconType.TIME_CHANGED -> Icons.Default.AccessTime
        EventIconType.LOCALE_CHANGED -> Icons.Default.Language
    }

    Icon(
        imageVector = imageVector,
        contentDescription = iconType.name,
        modifier = modifier,
        tint = tint
    )
}
