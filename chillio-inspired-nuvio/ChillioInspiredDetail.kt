package com.nuvio.tv.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

data class InspiredRating(val source: String, val score: String, val detail: String? = null)

@Composable
fun ChillioInspiredDetailPrototype(
    title: String,
    backdropUrl: String?,
    logoUrl: String? = null,
    metadata: String,
    synopsis: String,
    ratings: List<InspiredRating>,
    onPlay: () -> Unit,
    onToggleFavorite: () -> Unit,
    onMarkWatched: () -> Unit,
    onMoreSynopsis: () -> Unit,
    onTrailer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize().background(Color(0xFF080D11))) {
        AsyncImage(
            model = backdropUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(570.dp).align(Alignment.TopCenter),
        )
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0f to Color(0x22070B0E),
                    0.28f to Color(0x44070B0E),
                    0.56f to Color(0xE6080D11),
                    0.76f to Color(0xFF080D11),
                    1f to Color(0xFF080D11),
                )
            )
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 64.dp, end = 64.dp, top = 250.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            if (logoUrl != null) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(370.dp).height(92.dp),
                )
            } else {
                Text(title, color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.ExtraBold)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                DetailAction(label = "▶  Play", primary = true, onClick = onPlay)
                DetailAction(label = "♥", primary = false, onClick = onToggleFavorite)
            }
            DetailAction(label = "✓  Mark as Watched", primary = false, onClick = onMarkWatched, wide = true)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(44.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Column(modifier = Modifier.weight(1.05f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(metadata, color = Color(0xFFD3DBE0), fontSize = 15.sp)
                    Text(
                        synopsis,
                        color = Color(0xFFB9C3CA),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        "MORE  ›",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable(onClick = onMoreSynopsis).focusable(),
                    )
                }
                Column(
                    modifier = Modifier.weight(0.95f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    ratings.chunked(3).forEach { row ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            row.forEach { rating -> RatingPill(rating) }
                        }
                    }
                }
            }

            Spacer(Modifier.height(4.dp))
            Text("Trailers & More", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
            DetailAction(label = "Watch trailer", primary = false, onClick = onTrailer)
        }
    }
}

@Composable
private fun DetailAction(
    label: String,
    primary: Boolean,
    onClick: () -> Unit,
    wide: Boolean = false,
) {
    varFocusedAction(label) {
        Box(
            modifier = Modifier
                .width(if (wide) 244.dp else if (primary) 154.dp else 66.dp)
                .height(if (wide) 48.dp else 46.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(if (primary) Color.White else Color(0xCC1B242C))
                .onFocusChanged { focused -> actionFocusState[label] = focused.isFocused }
                .clickable(onClick = onClick)
                .focusable()
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                label,
                color = if (primary) Color(0xFF10161B) else Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

private val actionFocusState = mutableMapOf<String, Boolean>()

@Composable
private fun varFocusedAction(label: String, content: @Composable () -> Unit) {
    content()
}

@Composable
private fun RatingPill(rating: InspiredRating) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xB51A232B))
            .padding(horizontal = 13.dp, vertical = 9.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Text(rating.source, color = Color(0xFFB8C3CB), fontSize = 11.sp, maxLines = 1)
        Text(rating.score, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        rating.detail?.let {
            Text(it, color = Color(0xFF9AA7B0), fontSize = 10.sp, maxLines = 1)
        }
    }
}
