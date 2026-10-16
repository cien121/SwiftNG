package com.v2ray.ang.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// iOS widget style colors
val iOSCardColor = Color(0xFF1C1C1E)
val iOSGreen = Color(0xFF30D158)
val iOSRed = Color(0xFFFF453A)
val iOSBlue = Color(0xFF0A84FF)
val iOSOrange = Color(0xFFFF9F0A)

// SwiftNG: purple-red gradient theme
val SwiftPurple = Color(0xFF8B5CF6)
val SwiftRed = Color(0xFFEF4444)
val SwiftGradient = Brush.horizontalGradient(listOf(SwiftPurple, SwiftRed))
val SwiftGradientVertical = Brush.verticalGradient(listOf(SwiftPurple, SwiftRed))
// Subtle dark gradient sheen for cards (dark, not flashy)
val SwiftCardSheen = Brush.linearGradient(
    listOf(Color(0xFF23232A), Color(0xFF1C1C1E), Color(0xFF1E1B2E))
)

/**
 * Top status card - iOS widget style
 * Left: status dot + connected/disconnected
 * Right: speed (up/down)
 * Bottom: node name · latency · protocol
 */
@Composable
fun ChanStatusCard(
    isRunning: Boolean,
    statusText: String,
    nodeName: String,
    latency: String,
    protocol: String,
    upSpeed: String = "--",
    downSpeed: String = "--",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SwiftCardSheen)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left: status dot + text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(if (isRunning) iOSGreen else iOSRed)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isRunning) "已连接" else "未连接",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                // Right: speed
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "↓ $downSpeed",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = iOSBlue,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "↑ $upSpeed",
                        style = MaterialTheme.typography.bodyMedium,
                        color = iOSGreen,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            // Bottom: node info
            Text(
                text = "$nodeName · $latency · $protocol",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF98989F),
                maxLines = 1
            )
        }
    }
}

/**
 * Current node card - full width (SwiftNG: latency auto-refreshes every 60s, no manual test)
 */
@Composable
fun ChanQuickCards(
    nodeName: String,
    onNodeClick: () -> Unit,
    modifier: Modifier = Modifier,
    ipAddress: String = "",
    ipVersionTag: String = ""
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onNodeClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = iOSCardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "当前节点",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF98989F)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = nodeName.ifEmpty { "未选择" },
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                maxLines = 1
            )
            // SwiftBox: IP + IPv4/IPv6 tag
            if (ipAddress.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = ipAddress,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF98989F),
                        maxLines = 1,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    if (ipVersionTag.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (ipVersionTag == "IPv6") Color(0xFF8B5CF6).copy(alpha = 0.25f)
                                    else Color(0xFF3B82F6).copy(alpha = 0.25f)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = ipVersionTag,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (ipVersionTag == "IPv6") Color(0xFFC4B5FD) else Color(0xFF93C5FD),
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * ChanNG: Auto-select best server card - prominent full-width button
 */
@Composable
fun ChanAutoSelectCard(
    isTesting: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isTesting) Brush.linearGradient(listOf(Color(0xFF2C2C2E), Color(0xFF2C2C2E))) else SwiftGradient)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isTesting) "正在测速..." else "⚡ 自动选择最快节点",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
