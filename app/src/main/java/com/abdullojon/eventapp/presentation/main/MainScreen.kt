package com.abdullojon.eventapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.abdullojon.eventapp.presentation.events.EventsTabContent
import com.abdullojon.eventapp.presentation.stats.StatsTabContent
import com.abdullojon.eventapp.ui.theme.DarkBackground
import com.abdullojon.eventapp.ui.theme.NeonGreen
import com.abdullojon.eventapp.ui.theme.TextGray

class MainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(EventsTabItem) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = DarkBackground,
                bottomBar = {
                    BottomNavigationBar()
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar() {
    val tabNavigator = LocalTabNavigator.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            tab = EventsTabItem,
            isSelected = tabNavigator.current == EventsTabItem,
            onClick = { tabNavigator.current = EventsTabItem }
        )
        BottomNavItem(
            tab = StatsTabItem,
            isSelected = tabNavigator.current == StatsTabItem,
            onClick = { tabNavigator.current = StatsTabItem }
        )
    }
}

@Composable
private fun BottomNavItem(
    tab: Tab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val options = tab.options
    val color = if (isSelected) NeonGreen else TextGray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        options.icon?.let { icon ->
            Icon(
                painter = icon,
                contentDescription = options.title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = options.title,
            color = color,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(width = 20.dp, height = 3.dp)
                    .clip(RoundedCornerShape(1.5.dp))
                    .background(NeonGreen)
            )
        } else {
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

object EventsTabItem : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Events"
            val icon = rememberVectorPainter(Icons.Default.GridView)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        EventsTabContent()
    }
}

object StatsTabItem : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Stats"
            val icon = rememberVectorPainter(Icons.Default.BarChart)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        StatsTabContent()
    }
}