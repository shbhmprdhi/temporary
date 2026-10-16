package com.nuvio.tv.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

/**
 * Clean-room Chillio-inspired layout prototype.
 *
 * This is intentionally data-driven and does not replace HomeScreen yet. Adapt the existing
 * HomeUiState/ModernHomePresentation into these lightweight models, then wire callbacks to
 * HomeScreen's existing navigation and playback lambdas.
 */
data class InspiredHomeMedia(
    val id: String,
    val title: String,
    val posterUrl: String?,
    val backdropUrl: String? = null,
    val subtitle: String? = null,
    val progress: Float? = null,
)

data class InspiredHomeCategory(
    val title: String,
    val imageUrl: String?,
)

@Composable
fun ChillioInspiredHomePrototype(
    greeting: String,
    profileImageUrl: String?,
    dateLabel: String,
    accountLabel: String,
    categories: List<InspiredHomeCategory>,
    continueWatching: List<InspiredHomeMedia>,
    favorites: List<InspiredHomeMedia>,
    onSearch: () -> Unit,
    onMovies: () -> Unit,
    onShows: () -> Unit,
    onLive: () -> Unit,
    onSettings: () -> Unit,
    onMediaClick: (InspiredHomeMedia) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableIntStateOf(1) }
    val bg = Color(0xFF090D11)
    val surface = Color(0xFF151B22)
    val muted = Color(0xFF9BA7B3)
    val tabs = listOf("⌕", "Movies", "Shows", "Live", "⚙")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0f to Color(0xFF101A21),
                    0.52f to bg,
                    1f to Color(0xFF080A0D),
                )
            )
            .padding(horizontal = 52.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = profileImageUrl,
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(44.dp).clip(CircleShape).background(surface),
                )
                Spacer(Modifier.width(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(greeting, color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
                    Text("Your next favourite is waiting", color = muted, fontSize = 13.sp)
                }
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xCC151B22))
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                tabs.forEachIndexed { index, label ->
                    val active = selectedTab == index
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (active) Color(0xFFF4F6F8) else Color.Transparent)
                            .clickable {
                                selectedTab = index
                                when (index) {
                                    0 -> onSearch()
                                    1 -> onMovies()
                                    2 -> onShows()
                                    3 -> onLive()
                                    4 -> onSettings()
                                }
                            }
                            .focusable()
                            .padding(horizontal = if (index == 0 || index == 4) 17.dp else 22.dp, vertical = 11.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            label,
                            color = if (active) Color(0xFF11161B) else Color(0xFFD6DDE3),
                            fontSize = 14.sp,
                            fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                        )
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(dateLabel, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text(accountLabel, color = muted, fontSize = 12.sp)
            }
        }

        if (categories.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                items(categories, key = { it.title }) { category ->
                    CategoryTile(category = category, surface = surface)
                }
            }
        }

        if (continueWatching.isNotEmpty()) {
            MediaSection(
                title = "Continue Watching",
                items = continueWatching,
                landscape = true,
                onMediaClick = onMediaClick,
            )
        }

        if (favorites.isNotEmpty()) {
            MediaSection(
                title = "Favorites",
                items = favorites,
                landscape = false,
                onMediaClick = onMediaClick,
            )
        }
    }
}

@Composable
private fun CategoryTile(category: InspiredHomeCategory, surface: Color) {
    Box(
        modifier = Modifier
            .width(235.dp)
            .height(112.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(surface)
            .focusable(),
    ) {
        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.horizontalGradient(
                    0f to Color(0xF20A0D11),
                    0.55f to Color(0xA60A0D11),
                    1f to Color(0x080A0D11),
                )
            )
        )
        Text(
            category.title,
            color = Color.White,
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 18.dp, end = 92.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun MediaSection(
    title: String,
    items: List<InspiredHomeMedia>,
    landscape: Boolean,
    onMediaClick: (InspiredHomeMedia) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(items, key = { it.id }) { media ->
                MediaTile(
                    media = media,
                    landscape = landscape,
                    onClick = { onMediaClick(media) },
                )
            }
        }
    }
}

@Composable
private fun MediaTile(
    media: InspiredHomeMedia,
    landscape: Boolean,
    onClick: () -> Unit,
) {
    var focused by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .width(if (landscape) 300.dp else 132.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(if (focused == 1) 2.dp else 0.dp, Color.White),
                RoundedCornerShape(12.dp),
            )
            .background(Color(0xFF151B22))
            .onFocusChanged { focused = if (it.isFocused) 1 else 0 }
            .clickable(onClick = onClick)
            .focusable(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box {
            AsyncImage(
                model = if (landscape) (media.backdropUrl ?: media.posterUrl) else media.posterUrl,
                contentDescription = media.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(if (landscape) 16f / 8.8f else 2f / 3f)
                    .clip(RoundedCornerShape(12.dp)),
            )
            if (landscape && media.progress != null) {
                Box(
                    Modifier.align(Alignment.BottomStart).fillMaxWidth().height(4.dp)
                        .background(Color(0x77000000))
                ) {
                    Box(
                        Modifier.fillMaxWidth(media.progress.coerceIn(0f, 1f)).height(4.dp)
                            .background(Color(0xFFF3F5F7))
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Text(
                media.title,
                color = Color.White,
                fontSize = if (landscape) 15.sp else 13.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            media.subtitle?.let {
                Text(it, color = Color(0xFF9BA7B3), fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
